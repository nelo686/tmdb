package es.mrmoustard.tmdb.data.datasource.agreement

import es.mrmoustard.tmdb.data.entities.api.MovieDetailDto
import es.mrmoustard.tmdb.data.entities.api.WrapperDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int,
        @Query("region") region: String
    ): WrapperDto

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetailDto
}
