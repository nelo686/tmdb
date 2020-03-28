package es.mrmoustard.tmdb.di.list

import dagger.Subcomponent
import es.mrmoustard.tmdb.di.ActivityScope
import es.mrmoustard.tmdb.ui.main.MainActivity

@ActivityScope
@Subcomponent(modules = [ListModule::class])
interface ListComponent {
    fun inject(activity: MainActivity)
}
