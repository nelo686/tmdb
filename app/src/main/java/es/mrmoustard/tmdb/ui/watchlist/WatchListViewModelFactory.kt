package es.mrmoustard.tmdb.ui.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.mrmoustard.tmdb.domain.usecases.GetMoviesToWatchUseCase

class WatchListViewModelFactory(
    private val useCase: GetMoviesToWatchUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(WatchListViewModel::class.java)) {
            WatchListViewModel(useCase = useCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
}
