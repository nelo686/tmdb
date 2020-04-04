package es.mrmoustard.tmdb.di.home

import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase
import es.mrmoustard.tmdb.ui.home.HomeViewModel

@Module
class HomeModule {

    @Provides
    fun provideHomeViewModel(useCase: GetTopRatedUseCase): HomeViewModel =
        HomeViewModel(useCase = useCase)
}
