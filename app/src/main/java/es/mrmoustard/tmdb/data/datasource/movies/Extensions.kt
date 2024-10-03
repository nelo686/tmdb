package es.mrmoustard.tmdb.data.datasource.movies

import es.mrmoustard.tmdb.data.datasource.movies.entities.ErrorDto
import retrofit2.HttpException

private const val CODE_401 = 401
private const val UNKNOWN = "UNKNOWN"

fun Exception.parseErrorFormResponse(): ErrorDto =
    when (this) {
        is HttpException -> when (code()) {
            CODE_401 -> ErrorDto.UnauthorizedError
            else -> ErrorDto.DefaultError(message = this.message ?: UNKNOWN)
        }
        else -> ErrorDto.DefaultError(message = this.message ?: UNKNOWN)
    }
