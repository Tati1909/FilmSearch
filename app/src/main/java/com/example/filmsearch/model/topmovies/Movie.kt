package com.example.filmsearch.model.topmovies

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val crew: String? ="",
    val fullTitle: String? = "",
    val id: String? = "",
    val imDbRating: String? = "",
    val imDbRatingCount: String? = "",
    val image: String? = "",
    val rank: String? = "",
    val title: String? = "",
    val year: String? = ""
) : Parcelable
