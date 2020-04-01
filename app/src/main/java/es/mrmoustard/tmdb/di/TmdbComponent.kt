package es.mrmoustard.tmdb.di

import android.app.Application
import android.content.Context
import es.mrmoustard.tmdb.TmdbApp
import dagger.Component
import es.mrmoustard.tmdb.di.home.HomeSubComponent
import es.mrmoustard.tmdb.di.home.HomeModule
import javax.inject.Singleton

@Singleton
@Component(modules = [TmdbModule::class, DataModule::class])
interface TmdbComponent {

    fun inject(app: TmdbApp)

    fun getApplication(): Application?

    fun getContext(): Context?

    fun plus(module: HomeModule): HomeSubComponent
}
