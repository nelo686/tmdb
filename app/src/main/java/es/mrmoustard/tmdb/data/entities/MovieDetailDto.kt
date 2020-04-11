package es.mrmoustard.tmdb.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import es.mrmoustard.tmdb.domain.entities.Collection
import es.mrmoustard.tmdb.domain.entities.MovieDetail

@JsonClass(generateAdapter = true)
data class MovieDetailDto(
    val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name = "belongs_to_collection") val belongsToCollection: CollectionDto?,
    val budget: Int,
    val genres: List<GenreDto>,
    val homepage: String,
    val id: Int,
    @Json(name = "imdb_id") val imdbId: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "production_companies") val productionCompanies: List<ProductionCompanyDto>,
    @Json(name = "production_countries") val productionCountries: List<ProductionCountryDto>,
    @Json(name = "release_date") val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @Json(name = "spoken_languages") val spokenLanguages: List<SpokenLanguageDto>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int
)

fun MovieDetailDto.mapToDomain(): MovieDetail =
    MovieDetail(
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
        voteCount = voteCount
    )
