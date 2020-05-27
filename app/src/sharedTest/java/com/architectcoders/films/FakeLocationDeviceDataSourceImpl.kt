package com.architectcoders.films

import arrow.core.Either
import arrow.core.right
import es.mrmoustard.tmdb.data.datasource.location.LocationDeviceDataSource
import es.mrmoustard.tmdb.data.datasource.location.entities.ErrorDto
import es.mrmoustard.tmdb.data.datasource.location.entities.LocationDto
import javax.inject.Inject

class FakeLocationDeviceDataSourceImpl @Inject constructor() : LocationDeviceDataSource {

    override suspend fun getCountryCode(): Either<ErrorDto, LocationDto> =
        LocationDto(countryCode = "ES").right()
}
