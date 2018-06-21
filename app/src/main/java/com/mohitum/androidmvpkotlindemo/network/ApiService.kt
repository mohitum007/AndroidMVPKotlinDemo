package com.mohitum.androidmvpkotlindemo.network

import com.mohitum.androidmvpkotlindemo.constant.AppUrls
import com.mohitum.androidmvpkotlindemo.model.MovieData
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit based ApiService interface
 */
interface ApiService {

    /**
     * Get movies data service
     */
    @GET("?")
    fun getMovies(@Query(AppUrls.MOVIEDB_MOVIE_SEARCH) searchString: String,
                  @Query(AppUrls.MOVIEDB_API_KEY_QUERY) apiKey: String): Observable<MovieData>

    /**
     * Retrofit instance creation with debug logging interceptor
     */
    companion object {
        fun create(): ApiService {

            val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client : OkHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
            }.build()


            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(AppUrls.MOVIEDB_BASE_URL)
                    .client(client)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }

}