package es.mrmoustard.tmdb.ui.home

import es.mrmoustard.tmdb.domain.entities.Movie

sealed class HomeUiModel {
    object Loading : HomeUiModel()
    class Content(val movies: List<Movie>) : HomeUiModel()
    class Navigate(val movieId: Int) : HomeUiModel()
}
