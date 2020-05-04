package es.mrmoustard.tmdb.data.datasource.database

import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus
import javax.inject.Inject

class LocalMoviesDataSourceImpl @Inject constructor(
    private val db: AppDatabase
): LocalMoviesDataSource {

    override suspend fun findById(id: Int): MovieStatus? =
        db.dataBaseDao().findById(id = id)

    override suspend fun insert(item: MovieStatus) {
        db.dataBaseDao().insert(item = item)
    }

    override suspend fun delete(item: MovieStatus) {
        db.dataBaseDao().delete(item = item)
    }

    override suspend fun update(movieId: Int, favourite: Boolean, wannaWatchIt: Boolean) {
        db.dataBaseDao().update(movieId = movieId, favourite = favourite, wannaWatchIt = wannaWatchIt)
    }

    override suspend fun findMoviesToWatch(): List<MovieStatus> =
        db.dataBaseDao().findMoviesToWatch()

    override suspend fun findFavourites(): List<MovieStatus> =
        db.dataBaseDao().findFavourites()

}