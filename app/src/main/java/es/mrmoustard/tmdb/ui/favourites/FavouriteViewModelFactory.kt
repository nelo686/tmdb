package es.mrmoustard.tmdb.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.mrmoustard.tmdb.domain.usecases.GetFavouriteMoviesUseCase

class FavouriteViewModelFactory(
    private val useCase: GetFavouriteMoviesUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            FavouriteViewModel(useCase = useCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
}
