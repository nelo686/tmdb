package es.mrmoustard.tmdb

import android.app.Application
import androidx.room.Room
import es.mrmoustard.tmdb.data.db.MoviesDatabase
import es.mrmoustard.tmdb.di.DaggerTmdbComponent
import es.mrmoustard.tmdb.di.DataModule
import es.mrmoustard.tmdb.di.TmdbComponent
import es.mrmoustard.tmdb.di.TmdbModule

class TmdbApp : Application() {

    val component: TmdbComponent by lazy {
        DaggerTmdbComponent.builder()
            .tmdbModule(TmdbModule(app = this))
            .dataModule(
                DataModule(
                    baseUrl = BuildConfig.BASE_URL,
                    bearer = BuildConfig.BEARER
                )
            )
            .build()
    }

    lateinit var db: MoviesDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        component.inject(app = this)

//        db = Room.databaseBuilder(
//            this,
//            MoviesDatabase::class.java,
//            "movies-db"
//        ).build()
        db = MoviesDatabase.getInstance(context = this)
    }
}
