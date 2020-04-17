package es.mrmoustard.tmdb.ui.watchlist

import es.mrmoustard.tmdb.domain.entities.MovieFlags

sealed class WatchListUiModel {
    object Loading : WatchListUiModel()
    object EmptyState : WatchListUiModel()
    class Content(val movies: List<MovieFlags>) : WatchListUiModel()
    class Navigate(val movieId: Int) : WatchListUiModel()
}
