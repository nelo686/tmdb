package es.mrmoustard.tmdb.ui.favourites

import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus

sealed class FavouriteUiModel {
    object Loading : FavouriteUiModel()
    object EmptyState : FavouriteUiModel()
    class Content(val movies: List<MovieStatus>) : FavouriteUiModel()
    class Navigate(val movieId: Int) : FavouriteUiModel()
}
