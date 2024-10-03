package es.mrmoustard.tmdb.di.home

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import es.mrmoustard.tmdb.di.scope.FragmentScope
import es.mrmoustard.tmdb.ui.home.HomeFragment

@FragmentScope
@Subcomponent(modules = [HomeModule::class])
interface HomeSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment
        ): HomeSubComponent
    }

    fun inject(fragment: HomeFragment)
}
