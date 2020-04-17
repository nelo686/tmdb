package es.mrmoustard.tmdb.domain.usecases

import es.mrmoustard.tmdb.data.repository.MoviesRepository
import javax.inject.Inject

class GetFavouriteMoviesUseCase @Inject constructor(private val repository: MoviesRepository) {

    suspend fun execute() = repository.findFavouriteMovies()
}
