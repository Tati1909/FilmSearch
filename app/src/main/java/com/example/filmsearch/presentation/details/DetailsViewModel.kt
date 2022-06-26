package com.example.filmsearch.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmsearch.presentation.ScreenState
import com.example.filmsearch.repository.details.DetailsRepository
import com.example.filmsearch.repository.details.DetailsRepositoryImpl

class DetailsViewModel(
    private val detailsLiveData: MutableLiveData<ScreenState> = MutableLiveData(),
    private val detailsRepository: DetailsRepository = DetailsRepositoryImpl()
) : ViewModel() {

    fun loadMovies() {
        detailsLiveData.value = ScreenState.Loading
        detailsRepository.getMovieInfo()
    }
}