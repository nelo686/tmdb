package es.mrmoustard.tmdb.di.home

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.di.common.getViewModel
import es.mrmoustard.tmdb.domain.usecases.GetCountryCodeUseCase
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase
import es.mrmoustard.tmdb.ui.home.HomeViewModel

@Module
class HomeModule {

    @Provides
    fun viewModelProvider(
        fragment: Fragment,
        getTopRatedUseCase: GetTopRatedUseCase,
        getCountryCodeUseCase: GetCountryCodeUseCase
    ): HomeViewModel =
        fragment.getViewModel {
            HomeViewModel(
                getTopRatedUseCase = getTopRatedUseCase,
                getCountryCodeUseCase = getCountryCodeUseCase
            )
        }
}
