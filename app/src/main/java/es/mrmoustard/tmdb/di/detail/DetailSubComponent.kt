package es.mrmoustard.tmdb.di.detail

import dagger.BindsInstance
import dagger.Subcomponent
import es.mrmoustard.tmdb.di.scope.ActivityScope
import es.mrmoustard.tmdb.ui.detail.DetailActivity

@ActivityScope
@Subcomponent(modules = [DetailModule::class])
interface DetailSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: DetailActivity
        ): DetailSubComponent
    }

    fun inject(activity: DetailActivity)
}
