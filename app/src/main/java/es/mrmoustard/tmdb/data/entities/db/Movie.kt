package es.mrmoustard.tmdb.data.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.mrmoustard.tmdb.domain.entities.MovieDetail
import es.mrmoustard.tmdb.data.entities.api.*
import es.mrmoustard.tmdb.domain.entities.Collection

@Entity
data class Movie (
    @PrimaryKey(autoGenerate = true)
    val dbId: Int,
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: CollectionDto?,
    val budget: Int,
    val genres: List<GenreDto>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompanyDto>,
    val productionCountries: List<ProductionCountryDto>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguageDto>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val favourite: Boolean = false,
    val wannaWatchIt: Boolean = false
)

fun Movie.mapToDomain(): MovieDetail = MovieDetail(
    adult = adult,
    backdropPath = backdropPath,
    belongsToCollection = belongsToCollection?.mapToDomain() ?: Collection(),
    budget = budget,
    genres = genres.mapToDomain(),
    homepage = homepage,
    id = id,
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath ?: "",
    productionCompanies = productionCompanies.mapToDomain(),
    productionCountries = productionCountries.mapToDomain(),
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages.mapToDomain(),
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    favourite = favourite,
    wannaWatchIt = wannaWatchIt
)
