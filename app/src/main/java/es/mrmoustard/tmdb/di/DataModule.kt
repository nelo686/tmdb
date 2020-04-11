package es.mrmoustard.tmdb.di

import android.content.Context
import es.mrmoustard.tmdb.BuildConfig
import es.mrmoustard.tmdb.data.datasource.agreement.TmdbService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.data.db.MoviesDatabase
import es.mrmoustard.tmdb.data.repository.MoviesRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class DataModule(
    private val baseUrl: String,
    private val bearer: String
) {

    companion object {
        private const val BEARER = "Bearer"
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original: Request = chain.request()

                    val request = chain.request().newBuilder()
                        .header("Content-Type", "application/json;charset=utf-8")
                        .header("Authorization", "$BEARER $bearer")
                        .method(original.method, original.body)
                        .build()

                    return chain.proceed(request)
                }
            })
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): TmdbService =
        retrofit.create(TmdbService::class.java)

    @Provides
    fun provideMoviesRepository(
        service: TmdbService,
        db: MoviesDatabase
    ) = MoviesRepository(
        service = service,
        db = db
    )

    @Provides
    fun provideMovieDatabase(context: Context) =
        MoviesDatabase.getInstance(context = context)
}
