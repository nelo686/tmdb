package es.mrmoustard.tmdb.domain.usecases

import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus
import es.mrmoustard.tmdb.data.repository.MoviesRepository
import javax.inject.Inject

class UpdateOrInsertMovieStatusUseCase @Inject constructor(private val repository: MoviesRepository) {
    suspend fun execute(status: MovieStatus) = repository.updateOrInsertMovieStatus(status = status)
}
