package com.example.filmsearch.model.details

data class DetailsResponse(
    val companies: String,
    val directors: String,
    val errorMessage: String?,
    val fullTitle: String,
    val genres: String,
    val id: String,
    val imDbRating: String,
    val image: String,
    val languages: String,
    val plot: String,
    val releaseDate: String,
    val runtimeMins: String,
    val stars: String,
    val writers: String,
    val year: String
) {

    fun toDomain(): DetailsMovie = DetailsMovie(
        companies = companies,
        directors = directors,
        errorMessage = errorMessage,
        fullTitle = fullTitle,
        genres = genres,
        id = id,
        imDbRating = imDbRating,
        image = image,
        languages = languages,
        plot = plot,
        releaseDate = releaseDate,
        runtimeMins = runtimeMins,
        stars = stars,
        writers = writers,
        year = year
    )
}
