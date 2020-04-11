package es.mrmoustard.tmdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.mrmoustard.tmdb.domain.usecases.FindMovieFlagsUseCase
import es.mrmoustard.tmdb.domain.usecases.GetMovieDetailUseCase
import es.mrmoustard.tmdb.domain.usecases.UpdateOrInsertMovieFlagsUseCase
import es.mrmoustard.tmdb.ui.common.Scope
import es.mrmoustard.tmdb.ui.detail.DetailUiModel.Flags
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val findMovieFlagsUseCase: FindMovieFlagsUseCase,
    private val updateOrInsertMovieFlagsUseCase: UpdateOrInsertMovieFlagsUseCase
) : ViewModel(), Scope by Scope.Impl() {

    private var movieId: Int = -1
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
            this.movieId = it.id
            _model.value = DetailUiModel.Content(movie = it)
            _model.value = Flags(flags = findFlags())
        })
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }

    fun onFavouriteClicked() {
        launch {
            val found = findFlags()
            val flags = found.copy(favourite = !found.favourite)
            withContext(ioDispatcher) {
                updateOrInsertMovieFlagsUseCase.execute(flags = flags)
            }
            _model.value = Flags(flags = flags)
        }
    }

    private suspend fun findFlags() =
        withContext(ioDispatcher) {
            findMovieFlagsUseCase.execute(movieId = movieId)
        }

    fun onWannaWatchItClicked() {
        launch {
            val found = findFlags()
            val flags = found.copy(wannaWatchIt = !found.wannaWatchIt)
            withContext(ioDispatcher) {
                updateOrInsertMovieFlagsUseCase.execute(flags = flags)
            }
            _model.value = Flags(flags = flags)
        }
    }
}
