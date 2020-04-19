package es.mrmoustard.tmdb.di.detail

import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.domain.usecases.GetMovieDetailUseCase
import es.mrmoustard.tmdb.domain.usecases.UpdateOrInsertMovieFlagsUseCase
import es.mrmoustard.tmdb.ui.detail.DetailViewModel

@Module
class DetailModule {

    @Provides
    fun provideDetailViewModel(
        movieDetailUseCase: GetMovieDetailUseCase,
        updateOrInsertMovieFlagsUseCase: UpdateOrInsertMovieFlagsUseCase
    ): DetailViewModel = DetailViewModel(
        movieDetailUseCase = movieDetailUseCase,
        updateOrInsertMovieFlagsUseCase = updateOrInsertMovieFlagsUseCase
    )
}
