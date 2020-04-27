package es.mrmoustard.tmdb.di

import android.content.Context
import dagger.BindsInstance
import es.mrmoustard.tmdb.TmdbApp
import dagger.Component
import es.mrmoustard.tmdb.di.detail.DetailModule
import es.mrmoustard.tmdb.di.detail.DetailSubComponent
import es.mrmoustard.tmdb.di.favourite.FavouriteModule
import es.mrmoustard.tmdb.di.favourite.FavouriteSubComponent
import es.mrmoustard.tmdb.di.home.HomeModule
import es.mrmoustard.tmdb.di.home.HomeSubComponent
import es.mrmoustard.tmdb.di.watchlist.WatchListModule
import es.mrmoustard.tmdb.di.watchlist.WatchListSubComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface TmdbComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            dataModule: DataModule
        ): TmdbComponent
    }

    fun inject(app: TmdbApp)
    fun plus(module: HomeModule): HomeSubComponent
    fun plus(module: WatchListModule): WatchListSubComponent
    fun plus(module: FavouriteModule): FavouriteSubComponent
    fun plus(module: DetailModule): DetailSubComponent
}
