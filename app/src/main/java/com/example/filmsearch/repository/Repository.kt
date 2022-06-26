package com.example.filmsearch.repository

import com.example.filmsearch.model.Movie

interface Repository {
    fun getMoviesFromServer(): List<Movie>
}