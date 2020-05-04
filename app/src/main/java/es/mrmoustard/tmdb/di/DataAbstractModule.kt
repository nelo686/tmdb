package es.mrmoustard.tmdb.di

import dagger.Binds
import dagger.Module
import es.mrmoustard.tmdb.data.datasource.database.LocalMoviesDataSource
import es.mrmoustard.tmdb.data.datasource.database.LocalMoviesDataSourceImpl
import es.mrmoustard.tmdb.data.datasource.movies.MoviesRemoteDataSource
import es.mrmoustard.tmdb.data.datasource.movies.MoviesRemoteDataSourceImpl

@Module
abstract class DataAbstractModule {

    @Binds
    abstract fun provideMoviesRemoteDataSource(
        dataSource: MoviesRemoteDataSourceImpl
    ): MoviesRemoteDataSource

    @Binds
    abstract fun provideLocalFilmsDataSource(
        dataSource: LocalMoviesDataSourceImpl
    ): LocalMoviesDataSource
}
