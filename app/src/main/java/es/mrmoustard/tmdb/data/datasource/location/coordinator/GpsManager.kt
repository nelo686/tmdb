package es.mrmoustard.tmdb.data.datasource.location.coordinator

import android.Manifest
import android.app.Application
import android.content.Context
import android.location.*
import android.os.Bundle
import android.os.Looper
import androidx.annotation.RequiresPermission
import timber.log.Timber
import javax.inject.Inject

class GpsManager @Inject constructor(private val application: Application) {

    private val locationManager: LocationManager? by lazy {
        (application.getSystemService(Context.LOCATION_SERVICE) as? LocationManager?)
    }

    @RequiresPermission(allOf = [
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ])
    fun startLocationService(countryCodeResponse: (String) -> Unit) {
        locationManager?.requestSingleUpdate(
            LocationManager.GPS_PROVIDER,
            object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    val addresses = getAddresses(location)
                    countryCodeResponse(getCountryCode(addresses = addresses))
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    Timber.d("Status changed")
                }

                override fun onProviderEnabled(provider: String?) {
                    Timber.d("Location enabled")
                }

                override fun onProviderDisabled(provider: String?) {
                    countryCodeResponse("")
                    Timber.d("Location disabled")
                }
            },
            Looper.getMainLooper()
        )
    }

    private fun getAddresses(location: Location): List<Address> =
        Geocoder(application).getFromLocation(
            location.latitude,
            location.longitude,
            1
        )

    private fun getCountryCode(addresses: List<Address>): String =
        if (addresses.isNotEmpty()) {
            val code = addresses[0].countryCode
            Timber.d("Address :: $code")
            code
        } else {
            Timber.d("No address found")
            ""
        }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun getLastKnownCountryCode(): String {
        locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let { location ->
            val addresses = getAddresses(location)
            return getCountryCode(addresses = addresses)
        }

        return ""
    }
}
