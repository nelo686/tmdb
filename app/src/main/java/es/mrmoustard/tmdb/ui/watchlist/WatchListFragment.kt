package es.mrmoustard.tmdb.ui.watchlist

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
import es.mrmoustard.tmdb.databinding.FragmentWatchlistBinding
import es.mrmoustard.tmdb.domain.entities.Movie
import es.mrmoustard.tmdb.domain.entities.MovieFlags
import es.mrmoustard.tmdb.ui.detail.DetailActivity
import es.mrmoustard.tmdb.ui.home.ItemAdapter
import es.mrmoustard.tmdb.ui.main.MainActivity
import es.mrmoustard.tmdb.ui.watchlist.WatchListUiModel.*
import javax.inject.Inject

class WatchListFragment : Fragment() {

    @Inject
    lateinit var viewModel: WatchListViewModel

    private val component by lazy {
        (requireActivity() as MainActivity).component.addWatchListModule()
    }

    private lateinit var binding: FragmentWatchlistBinding

    private lateinit var adapter: ItemAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.create(fragment = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchlistBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ItemAdapter { viewModel.onMovieClicked(movieId = it) }

        binding.rvMovies.adapter = adapter
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(model: WatchListUiModel) {
        (activity as MainActivity).showLoading(model == Loading)

        when (model) {
            is Content -> setContentState(movies = model.movies)
            is EmptyState -> setEmptyState()
            is Navigate ->
                findNavController().navigate(
                    R.id.action_navigation_watchlist_to_detail_activity,
                    Bundle().apply { putInt(DetailActivity.MOVIE_ID, model.movieId) }
                )
        }
    }

    private fun setContentState(movies: List<MovieFlags>) {
        adapter.items = movies.map {
            Movie(id = it.id, title = it.title, backdropPath = it.backdropPath)
        }
        binding.rvMovies.visibility = View.VISIBLE
        binding.tvEmpty.visibility = View.GONE
    }

    private fun setEmptyState() {
        binding.rvMovies.visibility = View.GONE
        binding.tvEmpty.visibility = View.VISIBLE
    }

    override fun onResume() {
        viewModel.getMoviesToWatch()
        super.onResume()
    }
}
