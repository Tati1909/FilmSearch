package com.example.filmsearch.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmsearch.model.Movie
import com.example.filmsearch.presentation.ScreenState
import com.example.filmsearch.repository.Repository
import com.example.filmsearch.repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<ScreenState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) : ViewModel() {

    fun requestLiveData(): LiveData<ScreenState> {
    return liveDataToObserve
    }

    fun requestMovies() {
        liveDataToObserve.value = ScreenState.Loading
        Thread {
            liveDataToObserve.postValue(ScreenState.Success(repository.getMoviesFromServer()))
        }.start()
    }

}