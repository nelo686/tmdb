package es.mrmoustard.tmdb.data.entities

import es.mrmoustard.tmdb.domain.entities.Genre
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreDto(
    val id: Int,
    val name: String
)

fun GenreDto.mapToDomain(): Genre = Genre(id = id, name = name)

fun List<GenreDto>.mapToDomain(): List<Genre> {
    val list: MutableList<Genre> = mutableListOf()
    this.forEach { list.add(element = it.mapToDomain()) }
    return list
}
