package es.mrmoustard.tmdb.data.entities

import es.mrmoustard.tmdb.domain.entities.TopRatedWrapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WrapperDto(
    val page: Int,
    val results: List<MovieDto>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)

fun WrapperDto.mapToDomain(): TopRatedWrapper = TopRatedWrapper(
    page = page,
    results = results.mapToDomain(),
    totalPages = totalPages,
    totalResults = totalResults
)
