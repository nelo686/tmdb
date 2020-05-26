package es.mrmoustard.tmdb.ui.common

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import timber.log.Timber
import javax.inject.Inject

class LocationPermissionManager @Inject constructor(private val activity: AppCompatActivity) {

    companion object {
        private const val TOTAL_PERMISSIONS = 2
    }

    fun checkPermission(success: () -> Unit, failure: () -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            askPermission(success = success, failure = failure)
        } else {
            success()
        }
    }

    private fun askPermission(success: () -> Unit, failure: () -> Unit) {
        Dexter.withActivity(activity)
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (report.areAllPermissionsGranted()) {
                            Timber.d("All permissions are granted")
                            success()
                        } else if (report.areAllPermissionsReported()) {
                            Timber.d("Some permissions are not granted")
                            failure()
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            })
            .withErrorListener {
                Timber.d("Error :: ${it.name}")
            }
            .check()
    }

    private fun MultiplePermissionsReport.areAllPermissionsReported() =
        grantedPermissionResponses.size + deniedPermissionResponses.size == TOTAL_PERMISSIONS
}
