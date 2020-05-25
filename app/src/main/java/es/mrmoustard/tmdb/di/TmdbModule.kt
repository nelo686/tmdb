package es.mrmoustard.tmdb.di

import android.app.Application
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.data.datasource.location.coordinator.GpsManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
class TmdbModule {

    @Named("MainDispatcher")
    @Provides
    fun mainDispatcherProvider(): CoroutineDispatcher = Dispatchers.Main

    @Named("IODispatcher")
    @Provides
    fun ioDispatcherProvider() = Dispatchers.IO

    @Provides
    @Singleton
    fun gpsManagerProvider(application: Application) =
        GpsManager(application = application)
}