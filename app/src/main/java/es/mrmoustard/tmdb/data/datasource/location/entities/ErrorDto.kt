package es.mrmoustard.tmdb.data.datasource.location.entities

import es.mrmoustard.tmdb.domain.errors.DomainError

data class ErrorDto(val message: String)

fun ErrorDto.mapToDomain() = DomainError.DefaultError(message = message)
