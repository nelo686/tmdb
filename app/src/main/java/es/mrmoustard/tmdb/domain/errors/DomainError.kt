package es.mrmoustard.tmdb.domain.errors

sealed class DomainError {
    object Default : DomainError()
}
