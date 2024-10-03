package es.mrmoustard.tmdb.domain.errors

sealed class DomainError {
    data class DefaultError(val message: String) : DomainError()
    object UnauthorizedError : DomainError()
}
