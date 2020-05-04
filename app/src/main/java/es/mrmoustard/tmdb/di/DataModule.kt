package es.mrmoustard.tmdb.di

import android.app.Application
import androidx.room.Room
import es.mrmoustard.tmdb.BuildConfig
import es.mrmoustard.tmdb.data.datasource.movies.TmdbService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.DataModel
import es.mrmoustard.tmdb.data.datasource.database.AppDatabase
import es.mrmoustard.tmdb.data.datasource.database.LocalMoviesDataSource
import es.mrmoustard.tmdb.data.datasource.movies.MoviesRemoteDataSource
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
class DataModule {

    companion object {
        private const val BEARER = "Bearer"
    }

    @Provides
    @Singleton
    fun httpLoggingInterceptorProvider() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun okHttpClientProvider(
        dataModel: DataModel,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original: Request = chain.request()

                    val request = chain.request().newBuilder()
                        .header("Content-Type", "application/json;charset=utf-8")
                        .header("Authorization", "$BEARER ${dataModel.bearer}")
                        .method(original.method, original.body)
                        .build()

                    return chain.proceed(request)
                }
            })
            .build()

    @Provides
    @Singleton
    fun retrofitProvider(dataModel: DataModel, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(dataModel.baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    fun apiServiceProvider(retrofit: Retrofit): TmdbService =
        retrofit.create(TmdbService::class.java)

    @Provides
    fun movieDatabaseProvider(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "db").build()

    @Provides
    fun moviesRepositoryProvider(
        remote: MoviesRemoteDataSource,
        local: LocalMoviesDataSource
    ): MoviesRepository = MoviesRepository(remote = remote, local = local)
}
