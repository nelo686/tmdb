package es.mrmoustard.tmdb.di

import android.app.Application
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.data.datasource.location.coordinator.GpsManager
import javax.inject.Singleton

@Module
class TmdbModule {

    @Provides
    @Singleton
    fun gpsManagerProvider(application: Application) =
        GpsManager(application = application)
}