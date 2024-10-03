package es.mrmoustard.tmdb.data.datasource.movies.entities

import es.mrmoustard.tmdb.domain.entities.Genre
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreDto(
    val id: Int,
    val name: String
)

fun GenreDto.mapToDomain(): Genre =
    Genre(id = id, name = name)

fun List<GenreDto>.mapToDomain(): List<Genre> =
    this.map { it.mapToDomain() }
