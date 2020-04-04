package es.mrmoustard.tmdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.mrmoustard.tmdb.domain.usecases.GetMovieDetailUseCase
import es.mrmoustard.tmdb.ui.common.Scope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val useCase: GetMovieDetailUseCase
) : ViewModel(), Scope by Scope.Impl() {

    private val _model = MutableLiveData<DetailUiModel>()
    val model: LiveData<DetailUiModel>
        get() = _model

    init {
        initScope()
    }

    fun onViewCreated(movieId: Int) {
        getMovieDetails(movieId = movieId)
    }

    private fun getMovieDetails(movieId: Int) {
        launch {
            _model.value = DetailUiModel.Loading
            withContext(ioDispatcher) {
                useCase.execute(movieId = movieId)
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
}
