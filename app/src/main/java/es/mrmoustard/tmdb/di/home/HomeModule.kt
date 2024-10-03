package es.mrmoustard.tmdb.di.home

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.di.common.getViewModel
import es.mrmoustard.tmdb.domain.usecases.GetCountryCodeUseCase
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase
import es.mrmoustard.tmdb.ui.home.HomeViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

@Module
class HomeModule {

    @Provides
    fun viewModelProvider(
        @Named("MainDispatcher") mainDispatcher: CoroutineDispatcher,
        @Named("IODispatcher") ioDispatcher: CoroutineDispatcher,
        fragment: Fragment,
        getTopRatedUseCase: GetTopRatedUseCase,
        getCountryCodeUseCase: GetCountryCodeUseCase
    ): HomeViewModel =
        fragment.getViewModel {
            HomeViewModel(
                mainDispatcher = mainDispatcher,
                ioDispatcher = ioDispatcher,
                getTopRatedUseCase = getTopRatedUseCase,
                getCountryCodeUseCase = getCountryCodeUseCase
            )
        }
}
