package es.mrmoustard.tmdb.ui.favourites

import es.mrmoustard.tmdb.domain.entities.MovieFlags

sealed class FavouriteUiModel {
    object Loading : FavouriteUiModel()
    object EmptyState : FavouriteUiModel()
    class Content(val movies: List<MovieFlags>) : FavouriteUiModel()
    class Navigate(val movieId: Int) : FavouriteUiModel()
}
