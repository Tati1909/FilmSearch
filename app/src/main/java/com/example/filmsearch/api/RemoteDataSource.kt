package com.example.filmsearch.api

import com.example.filmsearch.BuildConfig
import com.example.filmsearch.model.details.DetailsResponse
import com.example.filmsearch.model.topmovies.TopMoviesResponse
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

private const val BASE_URL = "https://imdb-api.com/"

class RemoteDataSource {

    private val retrofit: MoviesService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        //Добавили Interceptor
        .client(createOkHttpClient(MovieApiInterceptor()))
        .build()
        .create(MoviesService::class.java)

    //Interceptor — часть библиотеки OkHttp. Посредством Interceptor можно смотреть в логах
    //запросы и ответы
    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    fun getMovies(callback: Callback<TopMoviesResponse>) {
        retrofit.getMovies(BuildConfig.MOVIE_API_KEY).enqueue(callback)
    }

    fun getDetails(
        callback: Callback<DetailsResponse>,
        titleId: String
    ) {
        retrofit.getDetails(
            BuildConfig.MOVIE_API_KEY,
            titleId
        ).enqueue(callback)
    }

    inner class MovieApiInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }
}