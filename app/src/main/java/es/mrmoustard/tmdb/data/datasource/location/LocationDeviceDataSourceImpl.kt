package es.mrmoustard.tmdb.data.datasource.location

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right
import es.mrmoustard.tmdb.data.datasource.location.entities.ErrorDto
import es.mrmoustard.tmdb.data.datasource.location.entities.LocationDto
import es.mrmoustard.tmdb.data.datasource.location.coordinator.LocationCoordinator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class LocationDeviceDataSourceImpl @Inject constructor(
    private val locationCoordinator: LocationCoordinator
) : LocationDeviceDataSource {

    @ExperimentalCoroutinesApi
    override suspend fun getCountryCode(): Either<ErrorDto, LocationDto> =
        with(locationCoordinator.getCountryCode()) {
            return if (this.isEmpty()) {
                Left(ErrorDto(message = "No country code has been found"))
            } else {
                Right(LocationDto(countryCode = this))
            }
        }
}
