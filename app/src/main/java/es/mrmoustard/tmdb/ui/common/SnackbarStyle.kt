package es.mrmoustard.tmdb.ui.common

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import es.mrmoustard.tmdb.R

sealed class SnackbarStyle(
    val message: String,
    @ColorRes val color: Int
) {

    fun getBackgroundColor(context: Context) =
        ContextCompat.getColor(context, color)
}

class DefaultSnackbarStyle(message: String) : SnackbarStyle(message = message, color = R.color.colorAccent)
class ErrorSnackbarStyle(message: String) : SnackbarStyle(message = message, color = R.color.red)
