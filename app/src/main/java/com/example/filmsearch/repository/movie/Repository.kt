package com.example.filmsearch.repository.movie

import com.example.filmsearch.model.details.DetailsResponse
import com.example.filmsearch.model.topmovies.TopMoviesResponse

interface Repository {

    fun getMovies(callback: retrofit2.Callback<TopMoviesResponse>)
}