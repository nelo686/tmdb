package es.mrmoustard.tmdb.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.mrmoustard.tmdb.domain.usecases.GetMovieDetailUseCase
import es.mrmoustard.tmdb.domain.usecases.UpdateOrInsertMovieFlagsUseCase

class DetailViewModelFactory(
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val updateOrInsertMovieFlagsUseCase: UpdateOrInsertMovieFlagsUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            DetailViewModel(
                movieDetailUseCase = movieDetailUseCase,
                updateOrInsertMovieFlagsUseCase = updateOrInsertMovieFlagsUseCase
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
}
