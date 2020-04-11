package es.mrmoustard.tmdb.di.detail

import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.domain.usecases.*
import es.mrmoustard.tmdb.ui.detail.DetailViewModel

@Module
class DetailModule {

    @Provides
    fun provideDetailViewModel(
        movieDetailUseCase: GetMovieDetailUseCase,
        findMovieFlagsUseCase: FindMovieFlagsUseCase,
        insertMovieFlagsUseCase: InsertMovieFlagsUseCase,
        updateMovieFlagsUseCase: UpdateMovieFlagsUseCase,
        deleteMovieFlagsUseCase: DeleteMovieFlagsUseCase
    ): DetailViewModel = DetailViewModel(
        movieDetailUseCase = movieDetailUseCase,
        findMovieFlagsUseCase = findMovieFlagsUseCase,
        insertMovieFlagsUseCase = insertMovieFlagsUseCase,
        updateMovieFlagsUseCase = updateMovieFlagsUseCase,
        deleteMovieFlagsUseCase = deleteMovieFlagsUseCase
    )
}
