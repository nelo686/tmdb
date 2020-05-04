package es.mrmoustard.tmdb.data.datasource.movies

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.mrmoustard.tmdb.data.datasource.movies.entities.ErrorDto
import es.mrmoustard.tmdb.data.datasource.movies.entities.MovieDetailDto
import es.mrmoustard.tmdb.data.datasource.movies.entities.WrapperDto
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val service: TmdbService
) : MoviesRemoteDataSource {

    override suspend fun getTopRated(
        page: Int,
        region: String
    ): Either<ErrorDto, WrapperDto> = try {
        service.getTopRated(page = page, region = region).right()
    } catch (e: Exception) {
        e.parseErrorFormResponse().left()
    }

    override suspend fun getMovieDetails(movieId: Int): Either<ErrorDto, MovieDetailDto> = try {
        service.getMovieDetails(movieId = movieId).right()
    } catch (e: Exception) {
        e.parseErrorFormResponse().left()
    }
}
