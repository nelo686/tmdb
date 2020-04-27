package es.mrmoustard.tmdb.di.favourite

import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.domain.usecases.GetFavouriteMoviesUseCase
import es.mrmoustard.tmdb.ui.favourites.FavouriteViewModel

@Module
class FavouriteModule {

    @Provides
    fun provideFavouriteViewModel(useCase: GetFavouriteMoviesUseCase): FavouriteViewModel =
        FavouriteViewModel(useCase = useCase)
}
