package es.mrmoustard.tmdb.data.datasource.movies

import arrow.core.Either
import es.mrmoustard.tmdb.data.datasource.movies.entities.ErrorDto
import es.mrmoustard.tmdb.data.datasource.movies.entities.MovieDetailDto
import es.mrmoustard.tmdb.data.datasource.movies.entities.WrapperDto

interface MoviesRemoteDataSource {
    suspend fun getTopRated(page: Int, region: String): Either<ErrorDto, WrapperDto>
    suspend fun getMovieDetails(movieId: Int): Either<ErrorDto, MovieDetailDto>
}
