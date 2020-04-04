package es.mrmoustard.tmdb.di.detail

import dagger.Subcomponent
import es.mrmoustard.tmdb.di.scope.ActivityScope
import es.mrmoustard.tmdb.ui.detail.DetailActivity

@ActivityScope
@Subcomponent(modules = [DetailModule::class])
interface DetailSubComponent {
    fun inject(activity: DetailActivity)
}
