package com.example.filmsearch.presentation

import com.example.filmsearch.model.topmovies.Movie

sealed class ScreenState {
    data class Success<out T>(val movieData: T) : ScreenState()
    data class Error(val error: Throwable) : ScreenState()
    object Loading : ScreenState()
}
