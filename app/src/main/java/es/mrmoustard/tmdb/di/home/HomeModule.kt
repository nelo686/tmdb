package es.mrmoustard.tmdb.di.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.domain.usecases.GetCountryCodeUseCase
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase
import es.mrmoustard.tmdb.ui.home.HomeViewModel
import es.mrmoustard.tmdb.ui.home.HomeViewModelFactory

@Module
class HomeModule {

    @Provides
    fun homeViewModelProvider(
        fragment: Fragment,
        getTopRatedUseCase: GetTopRatedUseCase,
        getCountryCodeUseCase: GetCountryCodeUseCase
    ): HomeViewModel =
        ViewModelProvider(
            fragment,
            HomeViewModelFactory(
                getTopRatedUseCase = getTopRatedUseCase,
                getCountryCodeUseCase = getCountryCodeUseCase
            )
        ).get(HomeViewModel::class.java)
}
