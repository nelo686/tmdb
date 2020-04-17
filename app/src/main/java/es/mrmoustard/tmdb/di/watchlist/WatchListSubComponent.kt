package es.mrmoustard.tmdb.di.watchlist

import dagger.Subcomponent
import es.mrmoustard.tmdb.di.scope.FragmentScope
import es.mrmoustard.tmdb.ui.watchlist.WatchListFragment

@FragmentScope
@Subcomponent(modules = [WatchListModule::class])
interface WatchListSubComponent {
    fun inject(fragment: WatchListFragment)
}
