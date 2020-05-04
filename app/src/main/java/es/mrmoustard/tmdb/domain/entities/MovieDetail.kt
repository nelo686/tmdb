package es.mrmoustard.tmdb.domain.entities

import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus

data class MovieDetail(
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: Collection,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val favourite: Boolean = false,
    val wannaWatchIt: Boolean = false
)

fun MovieDetail.toMovieStatus(): MovieStatus =
    MovieStatus(
        id = id,
        title = title,
        backdropPath = backdropPath,
        favourite = favourite,
        wannaWatchIt = wannaWatchIt
    )
