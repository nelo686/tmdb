package es.mrmoustard.tmdb.di.favourite

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.di.common.getViewModel
import es.mrmoustard.tmdb.domain.usecases.GetFavouriteMoviesUseCase
import es.mrmoustard.tmdb.ui.favourites.FavouriteViewModel

@Module
class FavouriteModule {

    @Provides
    fun viewModelProvider(
        fragment: Fragment,
        useCase: GetFavouriteMoviesUseCase
    ): FavouriteViewModel =
        fragment.getViewModel {
            FavouriteViewModel(useCase = useCase)
        }
}
