package es.mrmoustard.tmdb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase

class HomeViewModelFactory(private val useCase: GetTopRatedUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        HomeViewModel(useCase = useCase) as T
}
