package com.example.filmsearch

import com.example.filmsearch.model.Movie

interface MovieApi {

    //@GET("/api.themoviedb.org/3/movie/550?api_key=274f828ad283bd634ef4fc1ee4af255f")
    suspend fun getMovies(): Movie
}