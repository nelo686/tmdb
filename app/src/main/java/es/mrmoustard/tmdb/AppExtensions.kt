package es.mrmoustard.tmdb

import android.app.Activity

val Activity.app: TmdbApp
    get() = application as TmdbApp
