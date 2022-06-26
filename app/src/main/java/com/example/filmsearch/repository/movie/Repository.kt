package com.example.filmsearch.repository.movie

import com.example.filmsearch.model.TopMoviesResponse

interface Repository {
    fun getMovies( callback: retrofit2.Callback<TopMoviesResponse>)
}