package es.mrmoustard.tmdb.di.main

import es.mrmoustard.tmdb.data.datasource.location.LocationDeviceDataSource
import es.mrmoustard.tmdb.data.datasource.location.LocationDeviceDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MainAbstractModule {

    @Binds
    abstract fun provideLocationDeviceDataSource(
        dataSource: LocationDeviceDataSourceImpl
    ): LocationDeviceDataSource
}
