package es.mrmoustard.tmdb.domain.usecases

import es.mrmoustard.tmdb.data.repository.MoviesRepository
import es.mrmoustard.tmdb.domain.entities.MovieFlags
import javax.inject.Inject

class UpdateOrInsertMovieFlagsUseCase @Inject constructor(private val repository: MoviesRepository) {
    suspend fun execute(flags: MovieFlags) = repository.updateOrInsertMovieFlags(flags = flags)
}
