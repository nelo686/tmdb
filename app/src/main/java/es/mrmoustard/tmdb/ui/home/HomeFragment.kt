package es.mrmoustard.tmdb.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.Lazy
import es.mrmoustard.tmdb.R
import es.mrmoustard.tmdb.databinding.FragmentTopratedBinding
import es.mrmoustard.tmdb.ui.common.*
import es.mrmoustard.tmdb.ui.detail.DetailActivity
import es.mrmoustard.tmdb.ui.main.MainActivity
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var permissionManager: LocationPermissionManager

    @Inject
    lateinit var viewModelInjection: Lazy<HomeViewModel>

    private val viewModel: HomeViewModel by lazy {
        viewModelInjection.get()
    }

    private val component by lazy {
        (requireActivity() as MainActivity).component.addHomeModule().create(fragment = this)
    }

    private lateinit var binding: FragmentTopratedBinding

    private val adapter: ItemAdapter by lazy {
        ItemAdapter(
            imageBaseUrl = (requireActivity() as MainActivity).imageBaseUrl,
            listener = viewModel::onMovieClicked
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(fragment = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopratedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovies.adapter = adapter

        viewModel.run {
            model.observe(viewLifecycleOwner, Observer(::updateUi))
            detailTransition.observe(viewLifecycleOwner, EventObserver(::goToDetail))
            permissionTransition.observe(viewLifecycleOwner, EventObserver(::checkPermission))
        }
    }

    private fun updateUi(model: HomeUiModel) {
        (activity as MainActivity).showLoading(model == HomeUiModel.Loading)

        when (model) {
            is HomeUiModel.Content -> adapter.items = model.movies
            is HomeUiModel.ErrorResponse -> activity?.showMessage(
                view = binding.rvMovies,
                style = ErrorSnackbarStyle(message = getString(R.string.something_happen))
            )
            else -> {}
        }
    }

    private fun goToDetail(movieId: Int) {
        findNavController().navigate(
            R.id.action_navigation_home_to_detail_activity,
            Bundle().apply { putInt(DetailActivity.MOVIE_ID, movieId) }
        )
    }

    private fun checkPermission(perform: Boolean) {
        if (perform) {
            permissionManager.checkPermission(
                success = { viewModel.getTopRated() },
                failure = { viewModel.getTopRated() }
            )
        } else {
            viewModel.getTopRated()
        }
    }
}
