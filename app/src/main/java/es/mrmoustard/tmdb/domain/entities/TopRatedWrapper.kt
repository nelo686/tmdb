package es.mrmoustard.tmdb.domain.entities

data class TopRatedWrapper(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
