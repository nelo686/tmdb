package es.mrmoustard.tmdb.di.home

import dagger.Subcomponent
import es.mrmoustard.tmdb.di.FragmentScope
import es.mrmoustard.tmdb.ui.home.HomeFragment

@FragmentScope
@Subcomponent(modules = [HomeModule::class])
interface HomeSubComponent {
    fun inject(fragment: HomeFragment)
}
