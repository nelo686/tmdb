package es.mrmoustard.tmdb.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus
import es.mrmoustard.tmdb.domain.usecases.GetMoviesToWatchUseCase
import es.mrmoustard.tmdb.ui.common.Scope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WatchListViewModel(
    private val useCase: GetMoviesToWatchUseCase
) : ViewModel(), Scope by Scope.Impl() {

    private val _model = MutableLiveData<WatchListUiModel>()
    val model: LiveData<WatchListUiModel>
        get() {
            if (_model.value == null) getMoviesToWatch()
            return _model
        }

    init {
        initScope()
    }

    fun getMoviesToWatch() {
        launch {
            var result: List<MovieStatus> = emptyList()
            _model.value = WatchListUiModel.Loading
            withContext(ioDispatcher) {
                result = useCase.execute()
            }
            if (result.isEmpty()) {
                _model.value = WatchListUiModel.EmptyState
            } else {
                _model.value = WatchListUiModel.Content(movies = result)
            }
        }
    }

    fun onMovieClicked(movieId: Int) {
        _model.value = WatchListUiModel.Navigate(movieId = movieId)
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }
}
