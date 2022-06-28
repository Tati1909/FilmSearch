package com.example.filmsearch.repository.details

import com.example.filmsearch.model.details.DetailsResponse

interface DetailsRepository {

    fun getDetails(
        callback: retrofit2.Callback<DetailsResponse>,
        titleId: String
    )
}