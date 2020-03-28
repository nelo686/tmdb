package es.mrmoustard.tmdb.domain.entities

data class Collection(
    val id: Int,
    val name: String,
    val posterPath: String,
    val backdropPath: String
)
