package es.mrmoustard.tmdb.di.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase
import es.mrmoustard.tmdb.ui.home.HomeViewModel
import es.mrmoustard.tmdb.ui.home.HomeViewModelFactory

@Module
class HomeModule {

    @Provides
    fun provideHomeViewModel(
        fragment: Fragment,
        useCase: GetTopRatedUseCase
    ): HomeViewModel =
        ViewModelProvider(
            fragment,
            HomeViewModelFactory(
                useCase = useCase
            )
        ).get(HomeViewModel::class.java)
}
