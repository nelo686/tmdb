package es.mrmoustard.tmdb.di.watchlist

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import es.mrmoustard.tmdb.di.scope.FragmentScope
import es.mrmoustard.tmdb.ui.watchlist.WatchListFragment

@FragmentScope
@Subcomponent(modules = [WatchListModule::class])
interface WatchListSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment
        ): WatchListSubComponent
    }

    fun inject(fragment: WatchListFragment)
}
