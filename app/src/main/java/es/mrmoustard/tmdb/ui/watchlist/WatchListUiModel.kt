package es.mrmoustard.tmdb.ui.watchlist

import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus

sealed class WatchListUiModel {
    object Loading : WatchListUiModel()
    object EmptyState : WatchListUiModel()
    class Content(val movies: List<MovieStatus>) : WatchListUiModel()
    class Navigate(val movieId: Int) : WatchListUiModel()
}
