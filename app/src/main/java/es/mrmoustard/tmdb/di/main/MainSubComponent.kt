package es.mrmoustard.tmdb.di.main

import androidx.appcompat.app.AppCompatActivity
import dagger.BindsInstance
import dagger.Subcomponent
import es.mrmoustard.tmdb.di.favourite.FavouriteSubComponent
import es.mrmoustard.tmdb.di.home.HomeSubComponent
import es.mrmoustard.tmdb.di.scope.ActivityScope
import es.mrmoustard.tmdb.di.watchlist.WatchListSubComponent
import es.mrmoustard.tmdb.ui.main.MainActivity

@ActivityScope
@Subcomponent(modules = [MainAbstractModule::class])
interface MainSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: AppCompatActivity): MainSubComponent
    }

    fun inject(activity: MainActivity)
    fun addHomeModule(): HomeSubComponent.Factory
    fun addFavouriteModule(): FavouriteSubComponent.Factory
    fun addWatchListModule(): WatchListSubComponent.Factory
}
