package com.example.filmsearch.api

import com.example.filmsearch.BuildConfig
import com.example.filmsearch.model.details.DetailsResponse
import com.example.filmsearch.model.topmovies.TopMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {

    @GET("/en/API/Top250Movies/{app_key}")
    fun getMovies(
        @Path("app_key") app_key: String = BuildConfig.MOVIE_API_KEY,
    ): Call<TopMoviesResponse>

    @GET("/en/API/Title/{app_key}/{title_id}")
    fun getDetails(
        @Path("app_key") app_key: String = BuildConfig.MOVIE_API_KEY,
        @Path("title_id") titleId: String
    ): Call<DetailsResponse>
}