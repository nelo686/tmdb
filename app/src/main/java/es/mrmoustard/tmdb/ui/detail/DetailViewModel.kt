package es.mrmoustard.tmdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus
import es.mrmoustard.tmdb.domain.entities.toMovieStatus
import es.mrmoustard.tmdb.domain.usecases.GetMovieDetailUseCase
import es.mrmoustard.tmdb.domain.usecases.UpdateOrInsertMovieStatusUseCase
import es.mrmoustard.tmdb.ui.common.Scope
import es.mrmoustard.tmdb.ui.detail.DetailUiModel.Flags
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val updateOrInsertMovieStatusUseCase: UpdateOrInsertMovieStatusUseCase
) : ViewModel(), Scope by Scope.Impl() {

    private lateinit var movie: MovieStatus
    private val _model = MutableLiveData<DetailUiModel>()
    val model: LiveData<DetailUiModel>
        get() = _model

    init {
        initScope()
    }

    fun onViewCreated(movieId: Int) {
        launch {
            getMovieDetails(movieId = movieId)
        }
    }

    private suspend fun getMovieDetails(movieId: Int) {
        _model.value = DetailUiModel.Loading
        withContext(ioDispatcher) {
            movieDetailUseCase.execute(movieId = movieId)
        }.fold({
            _model.value = DetailUiModel.ErrorResponse
        }, {
            this.movie = it.toMovieStatus()
            _model.value = DetailUiModel.Content(movie = it)
        })
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }

    fun onFavouriteClicked() {
        movie = movie.copy(favourite = !movie.favourite)
        updateOrInsertMovieAtDatabase(item = movie)
    }

    private fun updateOrInsertMovieAtDatabase(item: MovieStatus) {
        launch {
            withContext(ioDispatcher) {
                updateOrInsertMovieStatusUseCase.execute(status = item)
            }
            _model.value = Flags(flags = item)
        }
    }

    fun onWannaWatchItClicked() {
        movie = movie.copy(wannaWatchIt = !movie.wannaWatchIt)
        updateOrInsertMovieAtDatabase(item = movie)
    }
}
