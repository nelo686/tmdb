package es.mrmoustard.tmdb.di

import android.app.Application
import android.content.Context
import es.mrmoustard.tmdb.TmdbApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TmdbModule(app: TmdbApp) {

    private val application: Application

    init {
        application = app
    }

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideContext(): Context = application
}
