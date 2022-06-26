package com.example.filmsearch.repository.movie

import com.example.filmsearch.api.RemoteDataSource
import com.example.filmsearch.model.TopMoviesResponse
import retrofit2.Callback

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun getMovies(
        callback: Callback<TopMoviesResponse>
    ) {
        remoteDataSource.getMovies(callback)
    }
}