package es.mrmoustard.tmdb.data.datasource.location.coordinator

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import javax.inject.Inject

class LocationCoordinator @Inject constructor(
    private val locationPermissionManager: LocationPermissionManager,
    private val gpsManager: GpsManager
) {

    @ExperimentalCoroutinesApi
    suspend fun getCountryCode(): String = suspendCancellableCoroutine { continuation ->
        locationPermissionManager.checkPermission(
            success = {
                gpsManager.startLocationService { countryCode ->
                    if (countryCode.isEmpty()) {
                        continuation.resume(gpsManager.getLastKnownCountryCode(), ::error)
                    } else {
                        continuation.resume(countryCode, ::error)
                    }
                }
            },
            failure = {
                continuation.resume("", ::error)
            }
        )
    }

    private fun error(throwable: Throwable) {
        Timber.e(throwable)
    }
}
