package es.mrmoustard.tmdb.ui.common

import android.text.SpannableString
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar

fun FragmentActivity.showMessage(view: View, style: SnackbarStyle) {
    val snackbar = Snackbar.make(view, style.message, Snackbar.LENGTH_SHORT)
    snackbar.setBackgroundTint(style.getBackgroundColor(context = this))
    snackbar.show()
}

fun SpannableString.spanWith(target: String, apply: SpannableBuilder.() -> Unit) {
    val builder = SpannableBuilder()
    apply(builder)

    val start = this.indexOf(target)
    val end = start + target.length

    for (what in builder.what) {
        setSpan(what, start, end, builder.flags)
    }
}

class SpannableBuilder {
    lateinit var what: List<Any>
    var flags: Int = 0
}

fun ImageView.tintColour(colour: Int) {
    setColorFilter(
        ContextCompat.getColor(this.context, colour),
        android.graphics.PorterDuff.Mode.SRC_IN)
}
