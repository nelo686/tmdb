package es.mrmoustard.tmdb.di.favourite

import dagger.Subcomponent
import es.mrmoustard.tmdb.di.scope.FragmentScope
import es.mrmoustard.tmdb.ui.favourites.FavouritesFragment

@FragmentScope
@Subcomponent(modules = [FavouriteModule::class])
interface FavouriteSubComponent {
    fun inject(fragment: FavouritesFragment)
}
