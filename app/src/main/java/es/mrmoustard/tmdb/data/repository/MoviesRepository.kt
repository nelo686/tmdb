package es.mrmoustard.tmdb.data.repository

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right
import es.mrmoustard.tmdb.data.datasource.database.LocalMoviesDataSource
import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus
import es.mrmoustard.tmdb.data.datasource.movies.MoviesRemoteDataSource
import es.mrmoustard.tmdb.data.datasource.movies.entities.mapToDomain
import es.mrmoustard.tmdb.data.datasource.movies.entities.setStatus
import es.mrmoustard.tmdb.domain.entities.MovieDetail
import es.mrmoustard.tmdb.domain.entities.TopRatedWrapper
import es.mrmoustard.tmdb.domain.errors.DomainError
import javax.inject.Singleton

@Singleton
class MoviesRepository(
    private val remote: MoviesRemoteDataSource,
    private val local: LocalMoviesDataSource
) {

    suspend fun getTopRated(page: Int, region: String): Either<DomainError, TopRatedWrapper> =
        remote.getTopRated(page = page, region = region).fold(
            { Left(it.mapToDomain()) },
            { Right(it.mapToDomain()) }
        )

    suspend fun getMovieDetails(movieId: Int): Either<DomainError, MovieDetail> =
        remote.getMovieDetails(movieId = movieId).fold(
            {
                Left(it.mapToDomain())
            }, {
                val details = it.setStatus(status = findItem(movieId = movieId))
                Right(details.mapToDomain())
            }
        )

    private suspend fun findItem(movieId: Int): MovieStatus =
        local.findById(id = movieId) ?: MovieStatus(id = movieId)

    private suspend fun insertItem(status: MovieStatus) {
        local.insert(item = status)
    }

    private suspend fun deleteItem(status: MovieStatus) {
        local.delete(item = status)
    }

    private suspend fun updateItem(status: MovieStatus) {
        local.update(
            movieId = status.id,
            favourite = status.favourite,
            wannaWatchIt = status.wannaWatchIt
        )
    }

    suspend fun updateOrInsertMovieStatus(status: MovieStatus) {
        val item = local.findById(id = status.id)
        when {
            item == null -> insertItem(status = status)
            !status.favourite && !status.wannaWatchIt -> deleteItem(status = item)
            else -> updateItem(status = status)
        }
    }

    suspend fun findMoviesToWatch(): List<MovieStatus> =
        local.findMoviesToWatch()

    suspend fun findFavouriteMovies(): List<MovieStatus> =
        local.findFavourites()
}
