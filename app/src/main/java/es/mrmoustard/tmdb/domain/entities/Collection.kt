package es.mrmoustard.tmdb.domain.entities

data class Collection(
    val id: Int = -1,
    val name: String = "",
    val posterPath: String = "",
    val backdropPath: String = ""
)
