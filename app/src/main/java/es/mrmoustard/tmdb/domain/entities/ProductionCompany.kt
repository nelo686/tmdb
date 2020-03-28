package es.mrmoustard.tmdb.domain.entities

data class ProductionCompany(
    val id: Int,
    val name: String,
    val logoPath: String,
    val originCountry: String
)
