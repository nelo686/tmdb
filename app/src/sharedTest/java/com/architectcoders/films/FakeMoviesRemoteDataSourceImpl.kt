package com.architectcoders.films

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.architectcoders.films.common.FileManager.readFromJson
import es.mrmoustard.tmdb.data.datasource.movies.MoviesRemoteDataSource
import es.mrmoustard.tmdb.data.datasource.movies.entities.ErrorDto
import es.mrmoustard.tmdb.data.datasource.movies.entities.MovieDetailDto
import es.mrmoustard.tmdb.data.datasource.movies.entities.WrapperDto
import javax.inject.Inject

class FakeMoviesRemoteDataSourceImpl @Inject constructor() : MoviesRemoteDataSource {

    override suspend fun getTopRated(page: Int, region: String): Either<ErrorDto, WrapperDto> =
        readFromJson<WrapperDto>(filePath = "/response_top_rated.json")?.right() ?: run {
            ErrorDto.DefaultError(message = "File not found").left()
        }

    override suspend fun getMovieDetails(movieId: Int): Either<ErrorDto, MovieDetailDto> =
        readFromJson<MovieDetailDto>(filePath = "/response_detail.json")?.right() ?: run {
            ErrorDto.DefaultError(message = "File not found").left()
        }
}
