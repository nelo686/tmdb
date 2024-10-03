package es.mrmoustard.tmdb.domain.usecases

import es.mrmoustard.tmdb.data.repository.MoviesRepository
import javax.inject.Inject

class GetTopRatedUseCase @Inject constructor(private val repository: MoviesRepository) {

    suspend fun execute(page: Int, region: String) =
        repository.getTopRated(page = page, region = region)
}
