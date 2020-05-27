package com.architectcoders.films

import es.mrmoustard.tmdb.data.datasource.database.LocalMoviesDataSource
import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus

class FakeLocalMoviesDataSourceImpl : LocalMoviesDataSource {
    override suspend fun findById(id: Int): MovieStatus? = null

    override suspend fun insert(item: MovieStatus) {
        // Nothing to implement.
    }

    override suspend fun delete(item: MovieStatus) {
        // Nothing to implement.
    }

    override suspend fun update(movieId: Int, favourite: Boolean, wannaWatchIt: Boolean) {
        // Nothing to implement.
    }

    override suspend fun findMoviesToWatch(): List<MovieStatus> = listOf()

    override suspend fun findFavourites(): List<MovieStatus> = listOf()
}
