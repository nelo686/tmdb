package es.mrmoustard.tmdb.di.detail

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.domain.usecases.GetMovieDetailUseCase
import es.mrmoustard.tmdb.domain.usecases.UpdateOrInsertMovieStatusUseCase
import es.mrmoustard.tmdb.ui.detail.DetailActivity
import es.mrmoustard.tmdb.ui.detail.DetailViewModel
import es.mrmoustard.tmdb.ui.detail.DetailViewModelFactory

@Module
class DetailModule {

    @Provides
    fun detailViewModelProvider(
        activity: DetailActivity,
        movieDetailUseCase: GetMovieDetailUseCase,
        updateOrInsertMovieStatusUseCase: UpdateOrInsertMovieStatusUseCase
    ): DetailViewModel =
        ViewModelProvider(
            activity,
            DetailViewModelFactory(
                movieDetailUseCase = movieDetailUseCase,
                updateOrInsertMovieStatusUseCase = updateOrInsertMovieStatusUseCase
            )
        ).get(DetailViewModel::class.java)
}
