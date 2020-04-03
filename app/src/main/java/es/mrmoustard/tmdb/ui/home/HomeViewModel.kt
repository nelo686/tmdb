package es.mrmoustard.tmdb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase
import es.mrmoustard.tmdb.ui.common.Scope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val useCase: GetTopRatedUseCase
) : ViewModel(), Scope by Scope.Impl() {

    private val _model = MutableLiveData<HomeUiModel>()
    val model: LiveData<HomeUiModel>
        get() {
            if (_model.value == null) getTopRated()
            return _model
        }

    init {
        initScope()
    }

    private fun getTopRated(page: Int = 1, region: String = "") {
        launch {
            _model.value = HomeUiModel.Loading
            withContext(ioDispatcher) {
                useCase.execute(page = page, region = region)
            }.fold({
                _model.value = HomeUiModel.ErrorResponse
            }, {
                _model.value = HomeUiModel.Content(movies = it.results)
            })
        }
    }

    fun onMovieClicked(movieId: Int) {
        _model.value = HomeUiModel.Navigate(movieId = movieId)
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }
}
