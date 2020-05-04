package es.mrmoustard.tmdb.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import es.mrmoustard.tmdb.TmdbApp
import dagger.Component
import es.mrmoustard.tmdb.DataModel
import es.mrmoustard.tmdb.di.detail.DetailSubComponent
import es.mrmoustard.tmdb.di.main.MainSubComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DataAbstractModule::class, TmdbModule::class])
interface TmdbComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance application: Application,
            @BindsInstance dataModel: DataModel
        ): TmdbComponent
    }

    fun inject(app: TmdbApp)
    fun addMainModule(): MainSubComponent.Factory
    fun addDetailModule(): DetailSubComponent.Factory
}
