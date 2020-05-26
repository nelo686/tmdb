package es.mrmoustard.tmdb.di.main

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.data.datasource.location.coordinator.LocationPermissionManager
import es.mrmoustard.tmdb.di.scope.ActivityScope

@Module
class MainModule {

    @ActivityScope
    @Provides
    fun locationManagerProvider(activity: AppCompatActivity) =
        LocationPermissionManager(activity = activity)
}
