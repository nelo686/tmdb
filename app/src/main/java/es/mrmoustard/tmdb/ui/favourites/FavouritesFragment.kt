package es.mrmoustard.tmdb.ui.favourites

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
import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus
import es.mrmoustard.tmdb.databinding.FragmentFavouritesBinding
import es.mrmoustard.tmdb.domain.entities.Movie
import es.mrmoustard.tmdb.ui.common.EventObserver
import es.mrmoustard.tmdb.ui.detail.DetailActivity
import es.mrmoustard.tmdb.ui.favourites.FavouriteUiModel.*
import es.mrmoustard.tmdb.ui.common.ItemAdapter
import es.mrmoustard.tmdb.ui.main.MainActivity
import javax.inject.Inject

class FavouritesFragment : Fragment() {

    @Inject
    lateinit var viewModelInjection: Lazy<FavouriteViewModel>

    private val viewModel: FavouriteViewModel by lazy {
        viewModelInjection.get()
    }

    private val component by lazy {
        (requireActivity() as MainActivity).component.addFavouriteModule().create(fragment = this)
    }

    private lateinit var binding: FragmentFavouritesBinding

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
        binding = FragmentFavouritesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovies.adapter = adapter

        viewModel.run {
            model.observe(viewLifecycleOwner, Observer(::updateUi))
            detailTransition.observe(viewLifecycleOwner, EventObserver(::goToDetail))
        }
    }

    private fun updateUi(model: FavouriteUiModel) {
        (activity as MainActivity).showLoading(model == Loading)

        when (model) {
            is Content -> setContentState(movies = model.movies)
            is EmptyState -> setEmptyState()
            else -> {}
        }
    }

    private fun setContentState(movies: List<MovieStatus>) {
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

    private fun goToDetail(movieId: Int) {
        findNavController().navigate(
            R.id.action_navigation_favourites_to_detail_activity,
            Bundle().apply { putInt(DetailActivity.MOVIE_ID, movieId) }
        )
    }

    override fun onResume() {
        viewModel.getMoviesToWatch()
        super.onResume()
    }
}
