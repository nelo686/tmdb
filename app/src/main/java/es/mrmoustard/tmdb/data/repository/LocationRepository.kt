package es.mrmoustard.tmdb.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.mrmoustard.tmdb.data.datasource.location.LocationDeviceDataSource
import es.mrmoustard.tmdb.data.datasource.location.entities.mapToDomain
import es.mrmoustard.tmdb.di.scope.ActivityScope
import es.mrmoustard.tmdb.domain.entities.Location
import es.mrmoustard.tmdb.domain.errors.DomainError
import javax.inject.Inject

@ActivityScope
class LocationRepository @Inject constructor(private val locationDevice: LocationDeviceDataSource) {

    suspend fun getCountryCode(): Either<DomainError, Location> =
        locationDevice.getCountryCode().fold(
            { error -> error.mapToDomain().left() },
            { location -> location.mapToDomain().right() }
        )
}
