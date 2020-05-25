package es.mrmoustard.tmdb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.core.Either
import es.mrmoustard.tmdb.domain.entities.TopRatedWrapper
import es.mrmoustard.tmdb.domain.errors.DomainError
import es.mrmoustard.tmdb.domain.usecases.GetCountryCodeUseCase
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase
import es.mrmoustard.tmdb.ui.common.Scope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class HomeViewModel(
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val getCountryCodeUseCase: GetCountryCodeUseCase
) : ViewModel(), Scope by Scope.Impl(mainDispatcher = mainDispatcher, ioDispatcher = ioDispatcher) {

    private val _model = MutableLiveData<HomeUiModel>()
    val model: LiveData<HomeUiModel>
        get() = _model

    init {
        initScope()
        getTopRated()
    }

    private fun getTopRated(page: Int = 1) {
        launch {
            _model.value = HomeUiModel.Loading
            withContext(ioDispatcher, getTopRatedMovies(page)).fold(
                {
                    Timber.d("Get top rated error")
                    _model.value = HomeUiModel.ErrorResponse
                },
                {
                    Timber.d("Get top rated success")
                    _model.value = HomeUiModel.Content(movies = it.results)
                }
            )
        }
    }

    private fun getTopRatedMovies(page: Int):
            suspend CoroutineScope.() -> Either<DomainError, TopRatedWrapper> =
        {
            getCountryCodeUseCase.execute().fold(
                {
                    Timber.d("Get top rated with error")
                    getTopRatedUseCase.execute(page = page, region = "")
                },
                { location ->
                    Timber.d("Get top rated with code = ${location.countryCode}")
                    getTopRatedUseCase.execute(page = page, region = location.countryCode)
                }
            )
        }

    fun onMovieClicked(movieId: Int) {
        _model.value = HomeUiModel.Navigate(movieId = movieId)
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }
}
