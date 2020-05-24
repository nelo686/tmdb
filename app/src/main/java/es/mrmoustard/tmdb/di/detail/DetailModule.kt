package es.mrmoustard.tmdb.di.detail

import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.di.common.getViewModel
import es.mrmoustard.tmdb.domain.usecases.GetMovieDetailUseCase
import es.mrmoustard.tmdb.domain.usecases.UpdateOrInsertMovieStatusUseCase
import es.mrmoustard.tmdb.ui.detail.DetailActivity
import es.mrmoustard.tmdb.ui.detail.DetailViewModel

@Module
class DetailModule {

    @Provides
    fun viewModelProvider(
        activity: DetailActivity,
        movieDetailUseCase: GetMovieDetailUseCase,
        updateOrInsertMovieStatusUseCase: UpdateOrInsertMovieStatusUseCase
    ): DetailViewModel =
        activity.getViewModel {
            DetailViewModel(
                movieDetailUseCase = movieDetailUseCase,
                updateOrInsertMovieStatusUseCase = updateOrInsertMovieStatusUseCase
            )
        }
}
