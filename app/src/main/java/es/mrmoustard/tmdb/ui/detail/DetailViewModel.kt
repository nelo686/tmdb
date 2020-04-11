package es.mrmoustard.tmdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.mrmoustard.tmdb.domain.entities.MovieFlags
import es.mrmoustard.tmdb.domain.usecases.*
import es.mrmoustard.tmdb.ui.common.Scope
import es.mrmoustard.tmdb.ui.detail.DetailUiModel.Flags
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val findMovieFlagsUseCase: FindMovieFlagsUseCase,
    private val insertMovieFlagsUseCase: InsertMovieFlagsUseCase,
    private val updateMovieFlagsUseCase: UpdateMovieFlagsUseCase,
    private val deleteMovieFlagsUseCase: DeleteMovieFlagsUseCase
) : ViewModel(), Scope by Scope.Impl() {

    private var movieId: Int = -1
    private val _model = MutableLiveData<DetailUiModel>()
    val model: LiveData<DetailUiModel>
        get() = _model

    init {
        initScope()
    }

    fun onViewCreated(movieId: Int) {
        getMovieDetails(movieId = movieId)

        //Check flags
        launch {
            _model.value = Flags(flags = findFlags())
        }
    }

    private fun getMovieDetails(movieId: Int) {
        launch {
            _model.value = DetailUiModel.Loading
            withContext(ioDispatcher) {
                movieDetailUseCase.execute(movieId = movieId)
            }.fold({
                _model.value = DetailUiModel.ErrorResponse
            }, {
                _model.value = DetailUiModel.Content(movie = it)
            })
        }
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }

    fun onFavouriteClicked() {
        launch {
            val found = findFlags()
            val flags = found.copy(favourite = found.favourite)
            setFavouriteFlag(flags = flags)
            _model.value = Flags(flags = flags)
        }
    }

    private suspend fun findFlags() =
        withContext(ioDispatcher) {
            findMovieFlagsUseCase.execute(movieId = movieId)
        }

    private suspend fun setFavouriteFlag(flags: MovieFlags) = with(flags) {
        withContext(ioDispatcher) {
            when {
                !favourite && !wannaWatchIt -> insertMovieFlagsUseCase.execute(flags = flags)
                else -> updateMovieFlagsUseCase.execute(flags = flags)
            }
        }
    }

    fun onWannaWatchItClicked() {
        launch {
            val found = findFlags()
            val flags = found.copy(wannaWatchIt = found.wannaWatchIt)
            setWannaWatchItFlag(flags = flags)
            _model.value = Flags(flags = flags)
        }
    }

    private suspend fun setWannaWatchItFlag(flags: MovieFlags) = with(flags) {
        withContext(ioDispatcher) {
            when {
                !favourite && !wannaWatchIt -> insertMovieFlagsUseCase.execute(flags = flags)
                else -> updateMovieFlagsUseCase.execute(flags = flags)
            }
        }
    }
}
