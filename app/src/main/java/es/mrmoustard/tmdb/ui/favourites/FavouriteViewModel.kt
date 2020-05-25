package es.mrmoustard.tmdb.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus
import es.mrmoustard.tmdb.domain.usecases.GetFavouriteMoviesUseCase
import es.mrmoustard.tmdb.ui.common.Scope
import es.mrmoustard.tmdb.ui.favourites.FavouriteUiModel.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteViewModel(
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher,
    private val useCase: GetFavouriteMoviesUseCase
) : ViewModel(), Scope by Scope.Impl(mainDispatcher = mainDispatcher, ioDispatcher = ioDispatcher) {

    private val _model = MutableLiveData<FavouriteUiModel>()
    val model: LiveData<FavouriteUiModel>
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
            _model.value = Loading
            withContext(ioDispatcher) {
                result = useCase.execute()
            }
            if (result.isEmpty()) {
                _model.value = EmptyState
            } else {
                _model.value = Content(movies = result)
            }
        }
    }

    fun onMovieClicked(movieId: Int) {
        _model.value = Navigate(movieId = movieId)
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }
}
