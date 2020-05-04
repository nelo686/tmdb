package es.mrmoustard.tmdb.data.datasource.movies.entities

import es.mrmoustard.tmdb.domain.errors.DomainError

sealed class ErrorDto {
    object UnauthorizedError : ErrorDto()
    data class HttpError(val code: Int, val message: String) : ErrorDto()
    data class DefaultError(val message: String) : ErrorDto()
}

private const val UNAUTHORIZED_CODE = 401
private const val UNKNOWN = "UNKNOWN"

fun ErrorDto.mapToDomain() = when (this) {
    is ErrorDto.HttpError -> {
        if (code == UNAUTHORIZED_CODE) DomainError.UnauthorizedError
        else DomainError.DefaultError(message = message)
    }
    is ErrorDto.DefaultError -> DomainError.DefaultError(message = message)
    else -> DomainError.DefaultError(message = UNKNOWN)
}
