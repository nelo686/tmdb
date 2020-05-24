package es.mrmoustard.tmdb.di.watchlist

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.di.common.getViewModel
import es.mrmoustard.tmdb.domain.usecases.GetMoviesToWatchUseCase
import es.mrmoustard.tmdb.ui.watchlist.WatchListViewModel

@Module
class WatchListModule {

    @Provides
    fun viewModelProvider(
        fragment: Fragment,
        useCase: GetMoviesToWatchUseCase
    ): WatchListViewModel =
        fragment.getViewModel {
            WatchListViewModel(useCase = useCase)
        }
}
