package es.mrmoustard.tmdb.di.watchlist

import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.domain.usecases.GetMoviesToWatchUseCase
import es.mrmoustard.tmdb.ui.watchlist.WatchListViewModel

@Module
class WatchListModule {

    @Provides
    fun provideWatchListViewModel(useCase: GetMoviesToWatchUseCase): WatchListViewModel =
        WatchListViewModel(useCase = useCase)
}
