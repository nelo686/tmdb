package es.mrmoustard.tmdb.data.datasource.location.entities

import es.mrmoustard.tmdb.domain.entities.Location as LocationDomain

data class LocationDto(val countryCode: String)

fun LocationDto.mapToDomain() = LocationDomain(countryCode = countryCode)
