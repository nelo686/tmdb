package es.mrmoustard.tmdb.data.repository

import es.mrmoustard.tmdb.domain.errors.DomainError
import retrofit2.HttpException

private const val CODE_401 = 401

private const val UNKNOWN = "UNKNOWN"

fun Exception.parseErrorFormResponse(): DomainError =
    when (this) {
        is HttpException -> when (this.code()) {
            CODE_401 -> DomainError.UnauthorizedError
            else -> DomainError.DefaultError(message = this.message ?: UNKNOWN)
        }
        else -> DomainError.DefaultError(message = this.message ?: UNKNOWN)
    }
