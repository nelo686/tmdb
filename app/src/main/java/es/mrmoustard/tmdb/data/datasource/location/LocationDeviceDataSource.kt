package es.mrmoustard.tmdb.data.datasource.location

import arrow.core.Either
import es.mrmoustard.tmdb.data.datasource.location.entities.ErrorDto
import es.mrmoustard.tmdb.data.datasource.location.entities.LocationDto

interface LocationDeviceDataSource {
    suspend fun getCountryCode(): Either<ErrorDto, LocationDto>
}
