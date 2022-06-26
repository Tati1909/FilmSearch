package com.example.filmsearch.api

import com.example.filmsearch.BuildConfig
import com.example.filmsearch.model.TopMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {
    @GET("/en/API/Top250Movies/{app_key}")
    fun getMovies(
        @Path("app_key") app_key: String = BuildConfig.MOVIE_API_KEY,
    ): Call<TopMoviesResponse>
}