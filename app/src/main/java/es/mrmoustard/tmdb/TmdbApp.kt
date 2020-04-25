package es.mrmoustard.tmdb

import android.app.Application
import es.mrmoustard.tmdb.data.db.MoviesDatabase
import es.mrmoustard.tmdb.di.DaggerTmdbComponent
import es.mrmoustard.tmdb.di.DataModule
import es.mrmoustard.tmdb.di.TmdbComponent

class TmdbApp : Application() {

    val component: TmdbComponent by lazy {
        DaggerTmdbComponent
            .factory()
            .create(
                context = this,
                dataModule = DataModule(
                    baseUrl = BuildConfig.BASE_URL,
                    bearer = BuildConfig.BEARER
                )
            )
    }

    lateinit var db: MoviesDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = MoviesDatabase.getInstance(context = this)
    }
}
