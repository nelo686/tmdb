package es.mrmoustard.tmdb.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import es.mrmoustard.tmdb.R
import es.mrmoustard.tmdb.app
import es.mrmoustard.tmdb.databinding.FragmentTopratedBinding
import es.mrmoustard.tmdb.di.home.HomeModule
import es.mrmoustard.tmdb.ui.common.ErrorSnackbarStyle
import es.mrmoustard.tmdb.ui.common.showMessage
import es.mrmoustard.tmdb.ui.detail.DetailActivity
import es.mrmoustard.tmdb.ui.main.MainActivity
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: HomeViewModel

    private val component by lazy {
        (activity as MainActivity).app.component.plus(module = HomeModule())
    }

    private lateinit var binding: FragmentTopratedBinding

    private lateinit var adapter: ItemAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(fragment = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopratedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ItemAdapter { viewModel.onMovieClicked(movieId = it) }

        binding.rvMovies.adapter = adapter
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(model: HomeUiModel) {
        (activity as MainActivity).showLoading(model == HomeUiModel.Loading)

        when (model) {
            is HomeUiModel.Content -> adapter.items = model.movies
            is HomeUiModel.ErrorResponse -> activity?.showMessage(
                view = binding.rvMovies,
                style = ErrorSnackbarStyle(message = getString(R.string.something_happen))
            )
            is HomeUiModel.Navigate -> {
                findNavController().navigate(
                    R.id.action_navigation_home_to_detail_activity,
                    Bundle().apply { putInt(DetailActivity.MOVIE_ID, model.movieId) }
                )
            }
        }
    }
}
