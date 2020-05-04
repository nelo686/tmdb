package es.mrmoustard.tmdb

import android.app.Application
import es.mrmoustard.tmdb.data.datasource.database.MoviesDatabase
import es.mrmoustard.tmdb.di.DaggerTmdbComponent
import es.mrmoustard.tmdb.di.TmdbComponent
import timber.log.Timber

class TmdbApp : Application() {

    val component: TmdbComponent by lazy {
        DaggerTmdbComponent
            .factory()
            .create(
                context = this,
                application = this,
                dataModel = DataModel(
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

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
