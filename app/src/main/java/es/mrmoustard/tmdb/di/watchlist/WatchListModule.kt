package es.mrmoustard.tmdb.di.watchlist

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.di.common.getViewModel
import es.mrmoustard.tmdb.domain.usecases.GetMoviesToWatchUseCase
import es.mrmoustard.tmdb.ui.watchlist.WatchListViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

@Module
class WatchListModule {

    @Provides
    fun viewModelProvider(
        @Named("MainDispatcher") mainDispatcher: CoroutineDispatcher,
        @Named("IODispatcher") ioDispatcher: CoroutineDispatcher,
        fragment: Fragment,
        useCase: GetMoviesToWatchUseCase
    ): WatchListViewModel =
        fragment.getViewModel {
            WatchListViewModel(
                mainDispatcher = mainDispatcher,
                ioDispatcher = ioDispatcher,
                useCase = useCase
            )
        }
}
