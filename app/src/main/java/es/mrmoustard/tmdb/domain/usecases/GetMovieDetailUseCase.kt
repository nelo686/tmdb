package es.mrmoustard.tmdb.domain.usecases

import es.mrmoustard.tmdb.data.repository.MoviesRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MoviesRepository) {

    suspend fun execute(movieId: Int) =
        repository.getMovieDetails(movieId = movieId)
}
