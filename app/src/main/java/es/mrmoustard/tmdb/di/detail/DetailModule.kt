package es.mrmoustard.tmdb.di.detail

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.domain.usecases.GetMovieDetailUseCase
import es.mrmoustard.tmdb.domain.usecases.UpdateOrInsertMovieFlagsUseCase
import es.mrmoustard.tmdb.ui.detail.DetailActivity
import es.mrmoustard.tmdb.ui.detail.DetailViewModel
import es.mrmoustard.tmdb.ui.detail.DetailViewModelFactory

@Module
class DetailModule {

    @Provides
    fun provideDetailViewModel(
        activity: DetailActivity,
        movieDetailUseCase: GetMovieDetailUseCase,
        updateOrInsertMovieFlagsUseCase: UpdateOrInsertMovieFlagsUseCase
    ): DetailViewModel =
        ViewModelProvider(
            activity,
            DetailViewModelFactory(
                movieDetailUseCase = movieDetailUseCase,
                updateOrInsertMovieFlagsUseCase = updateOrInsertMovieFlagsUseCase
            )
        ).get(DetailViewModel::class.java)
}
