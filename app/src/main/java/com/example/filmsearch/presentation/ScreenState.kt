package com.example.filmsearch.presentation

import com.example.filmsearch.model.Movie

sealed class ScreenState {
    data class Success(val movieData: List<Movie>) : ScreenState()
    data class Error(val error: Throwable) : ScreenState()
    object Loading : ScreenState()
}
