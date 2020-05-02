package es.mrmoustard.tmdb.di.favourite

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import es.mrmoustard.tmdb.di.scope.FragmentScope
import es.mrmoustard.tmdb.ui.favourites.FavouritesFragment

@FragmentScope
@Subcomponent(modules = [FavouriteModule::class])
interface FavouriteSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment
        ): FavouriteSubComponent
    }

    fun inject(fragment: FavouritesFragment)
}
