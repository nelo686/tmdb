package es.mrmoustard.tmdb.common

import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus
import es.mrmoustard.tmdb.data.datasource.movies.entities.*

class StubData {
    companion object {
        val movies = listOf(
            MovieStatus(
                id = 496243,
                title = "Parasite",
                backdropPath = "/ApiBzeaa95TNYliSbQ8pJv4Fje7.jpg"
            ),
            MovieStatus(
                id = 680,
                title = "Pulp Fiction",
                backdropPath = "/suaEOtk1N1sgg2MTM7oZd2cfVp3.jpg"
            )
        )

        val movieDetail = MovieDetailDto(
            adult = false,
            backdropPath = "/wooZWiC4NWH0ahCSUOogEmVejHo.jpg",
            belongsToCollection = null,
            budget = 75000,
            genres = listOf(
                GenreDto(id = 35, name = "Comedy"),
                GenreDto(id = 10751, name = "Family")
            ),
            homepage = "",
            id = 640344,
            imdbId = "tt11559652",
            originalLanguage = "it",
            originalTitle = "Me contro Te: Il film - La vendetta del Signor S",
            overview = "Luì and Sofì fight the terrible Signor S once again, this time he will be revealed to the public!!!",
            popularity = 12.453,
            posterPath = "/4XzbcAKdX4n3aWfzBjjeAlm68S3.jpg",
            productionCompanies = listOf(
                ProductionCompanyDto(
                    id = 10975,
                    logoPath = null,
                    name = "Colorado Film Production",
                    originCountry = ""
                ),
                ProductionCompanyDto(
                    id = 12923,
                    logoPath = null,
                    name = "Warner Bros Pictures Italia",
                    originCountry = "IT"
                )
            ),
            productionCountries = listOf(
                ProductionCountryDto(iso31661 = "IT", name = "Italy")
            ),
            releaseDate = "2020-01-17",
            revenue = 0,
            runtime = 0,
            spokenLanguages = listOf(
                SpokenLanguageDto(iso6391 = "it", name = "Italiano")
            ),
            status = "Released",
            tagline = "",
            title = "Me contro Te: Il film - La vendetta del Signor S",
            video = false,
            voteAverage = 8.8,
            voteCount = 273,
            favourite = false,
            wannaWatchIt = false
        )

        val filmStatus = MovieStatus(
            id = 640344,
            title = "Me contro Te: Il film - La vendetta del Signor S",
            backdropPath = "/wooZWiC4NWH0ahCSUOogEmVejHo.jpg",
            favourite = true,
            wannaWatchIt = false
        )
    }
}