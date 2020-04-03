package es.mrmoustard.tmdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import es.mrmoustard.tmdb.R
import es.mrmoustard.tmdb.app
import es.mrmoustard.tmdb.di.home.HomeModule
import es.mrmoustard.tmdb.ui.common.ErrorSnackbarStyle
import es.mrmoustard.tmdb.ui.common.inflate
import es.mrmoustard.tmdb.ui.common.showMessage
import es.mrmoustard.tmdb.ui.detail.DetailActivity
import es.mrmoustard.tmdb.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_toprated.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: HomeViewModel

    private val component by lazy {
        (activity as MainActivity).app.component.plus(HomeModule())
    }

    private lateinit var adapter: TopRatedAdapter

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container.inflate(R.layout.fragment_toprated)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(fragment = this)

        adapter = TopRatedAdapter { viewModel.onMovieClicked(movieId = it) }
        rvMovies.adapter = adapter
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: HomeUiModel) {
        (activity as MainActivity).showLoading(model == HomeUiModel.Loading)

        when (model) {
            is HomeUiModel.Content -> adapter.items = model.movies
            is HomeUiModel.ErrorResponse -> activity?.showMessage(
                view = rvMovies,
                style = ErrorSnackbarStyle(message = getString(R.string.something_happen))
            )
            is HomeUiModel.Navigate -> {
                context?.let {
                    startActivity(DetailActivity.create(context = it, movieId = model.movieId))
                }
            }
        }
    }
}
