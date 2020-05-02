package es.mrmoustard.tmdb.di.favourite

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.domain.usecases.GetFavouriteMoviesUseCase
import es.mrmoustard.tmdb.ui.favourites.FavouriteViewModel
import es.mrmoustard.tmdb.ui.favourites.FavouriteViewModelFactory

@Module
class FavouriteModule {

    @Provides
    fun provideFavouriteViewModel(
        fragment: Fragment,
        useCase: GetFavouriteMoviesUseCase
    ): FavouriteViewModel =
        ViewModelProvider(
            fragment,
            FavouriteViewModelFactory(useCase = useCase)
        ).get(FavouriteViewModel::class.java)
}
