package com.example.filmsearch.repository.details

import com.example.filmsearch.api.RemoteDataSource
import com.example.filmsearch.model.details.DetailsResponse
import retrofit2.Callback

class DetailsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : DetailsRepository {

    override fun getDetails(
        callback: Callback<DetailsResponse>,
        titleId: String
    ) {
        remoteDataSource.getDetails(callback, titleId)
    }
}