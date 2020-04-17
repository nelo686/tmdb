package es.mrmoustard.tmdb.domain.usecases

import es.mrmoustard.tmdb.data.repository.MoviesRepository
import javax.inject.Inject

class GetMoviesToWatchUseCase @Inject constructor(private val repository: MoviesRepository) {

    suspend fun execute() = repository.findMoviesToWatch()
}
