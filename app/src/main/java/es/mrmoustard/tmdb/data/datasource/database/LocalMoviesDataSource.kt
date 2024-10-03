package es.mrmoustard.tmdb.data.datasource.database

import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus

interface LocalMoviesDataSource {

    suspend fun findById(id: Int): MovieStatus?
    suspend fun insert(item: MovieStatus)
    suspend fun delete(item: MovieStatus)
    suspend fun update(movieId: Int, favourite: Boolean = false, wannaWatchIt: Boolean = false)
    suspend fun findMoviesToWatch(): List<MovieStatus>
    suspend fun findFavourites(): List<MovieStatus>
}