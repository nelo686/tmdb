package es.mrmoustard.tmdb.data.repository

import arrow.core.Either
import es.mrmoustard.tmdb.data.datasource.agreement.TmdbService
import es.mrmoustard.tmdb.data.entities.mapToDomain
import es.mrmoustard.tmdb.domain.entities.MovieDetail
import es.mrmoustard.tmdb.domain.entities.TopRatedWrapper
import es.mrmoustard.tmdb.domain.errors.DomainError
import es.mrmoustard.tmdb.ui.common.Scope
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val service: TmdbService
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
        val response = service.getMovieDetails(movieId = movieId)
        Either.right(response.mapToDomain())
    } catch (e: Exception) {
        Either.left(e.parseErrorFormResponse())
    }
}
