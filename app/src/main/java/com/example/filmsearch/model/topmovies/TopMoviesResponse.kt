package com.example.filmsearch.model.topmovies

data class TopMoviesResponse(
    val errorMessage: String,
    val items: List<Movie>
) {

    fun toDomain() = items.map {
        Movie(
            crew = it.crew,
            fullTitle = it.fullTitle,
            id = it.id,
            imDbRating = it.imDbRating,
            imDbRatingCount = it.imDbRatingCount,
            image = it.image,
            rank = it.rank,
            title = it.title,
            year = it.year,
        )
    }
}