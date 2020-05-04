package es.mrmoustard.tmdb.domain.usecases

import es.mrmoustard.tmdb.data.repository.LocationRepository
import javax.inject.Inject

class GetCountryCodeUseCase @Inject constructor(private val repository: LocationRepository) {

    suspend fun execute() = repository.getCountryCode()
}