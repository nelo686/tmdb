package es.mrmoustard.tmdb.data.entities

import es.mrmoustard.tmdb.domain.entities.Collection
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectionDto(
    val id: Int,
    val name: String,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String
)

fun CollectionDto.mapToDomain(): Collection =
    Collection(
        id = id,
        name = name,
        posterPath = posterPath ?: "",
        backdropPath = backdropPath
    )
