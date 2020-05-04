package es.mrmoustard.tmdb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.mrmoustard.tmdb.domain.usecases.GetCountryCodeUseCase
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase

class HomeViewModelFactory(
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val getCountryCodeUseCase: GetCountryCodeUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(
                getTopRatedUseCase = getTopRatedUseCase,
                getCountryCodeUseCase = getCountryCodeUseCase
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
}
