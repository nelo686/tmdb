package es.mrmoustard.tmdb.di.favourite

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.di.common.getViewModel
import es.mrmoustard.tmdb.domain.usecases.GetFavouriteMoviesUseCase
import es.mrmoustard.tmdb.ui.favourites.FavouriteViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

@Module
class FavouriteModule {

    @Provides
    fun viewModelProvider(
        @Named("MainDispatcher") mainDispatcher: CoroutineDispatcher,
        @Named("IODispatcher") ioDispatcher: CoroutineDispatcher,
        fragment: Fragment,
        useCase: GetFavouriteMoviesUseCase
    ): FavouriteViewModel =
        fragment.getViewModel {
            FavouriteViewModel(
                mainDispatcher = mainDispatcher,
                ioDispatcher = ioDispatcher,
                useCase = useCase
            )
        }
}
