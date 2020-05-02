package es.mrmoustard.tmdb.di.watchlist

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.domain.usecases.GetMoviesToWatchUseCase
import es.mrmoustard.tmdb.ui.watchlist.WatchListViewModel
import es.mrmoustard.tmdb.ui.watchlist.WatchListViewModelFactory

@Module
class WatchListModule {

    @Provides
    fun provideWatchListViewModel(
        fragment: Fragment,
        useCase: GetMoviesToWatchUseCase
    ): WatchListViewModel =
        ViewModelProvider(
            fragment,
            WatchListViewModelFactory(useCase = useCase)
        ).get(WatchListViewModel::class.java)
}
