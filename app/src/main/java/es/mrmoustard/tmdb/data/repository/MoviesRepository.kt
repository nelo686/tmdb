package es.mrmoustard.tmdb.data.repository

import arrow.core.Either
import es.mrmoustard.tmdb.data.datasource.agreement.TmdbService
import es.mrmoustard.tmdb.data.db.MoviesDatabase
import es.mrmoustard.tmdb.data.entities.mapToDomain
import es.mrmoustard.tmdb.data.entities.setFlags
import es.mrmoustard.tmdb.domain.entities.MovieDetail
import es.mrmoustard.tmdb.domain.entities.MovieFlags
import es.mrmoustard.tmdb.domain.entities.TopRatedWrapper
import es.mrmoustard.tmdb.domain.errors.DomainError
import es.mrmoustard.tmdb.ui.common.Scope

class MoviesRepository(
    private val service: TmdbService,
    private val db: MoviesDatabase
) : Scope by Scope.Impl() {

    init {
        initScope()
    }

    suspend fun getTopRated(page: Int, region: String): Either<DomainError, TopRatedWrapper> = try {
        val wrapperDto = service.getTopRated(page = page, region = region)
        Either.right(wrapperDto.mapToDomain())
    } catch (e: Exception) {
        Either.left(e.parseErrorFormResponse())
    }

    suspend fun getMovieDetails(movieId: Int): Either<DomainError, MovieDetail> = try {
        var detailDto = service.getMovieDetails(movieId = movieId)
        detailDto = detailDto.setFlags(flags = findMovieFlags(movieId = movieId))
        Either.right(detailDto.mapToDomain())
    } catch (e: Exception) {
        Either.left(e.parseErrorFormResponse())
    }

    private suspend fun findMovieFlags(movieId: Int): MovieFlags =
        db.movieDao().findById(id = movieId) ?: MovieFlags(id = movieId)

    suspend fun insertMovieFlags(flags: MovieFlags) {
        db.movieDao().insert(flags = flags)
    }

    suspend fun deleteMovieFlags(flags: MovieFlags) {
        db.movieDao().delete(flags = flags)
    }

    suspend fun updateMovieFlags(flags: MovieFlags) {
        db.movieDao().update(
            movieId = flags.id,
            favourite = flags.favourite,
            wannaWatchIt = flags.wannaWatchIt
        )
    }

    suspend fun updateOrInsertMovieFlags(flags: MovieFlags) {
        val item = db.movieDao().findById(id = flags.id)
        when {
            item == null -> insertMovieFlags(flags = flags)
            !flags.favourite && !flags.wannaWatchIt -> deleteMovieFlags(flags = item)
            else -> updateMovieFlags(flags = flags)
        }
    }

    suspend fun findMoviesToWatch(): List<MovieFlags> =
        db.movieDao().findMoviesToWatch()

    suspend fun findFavouriteMovies(): List<MovieFlags> =
        db.movieDao().findFavourites()
}
