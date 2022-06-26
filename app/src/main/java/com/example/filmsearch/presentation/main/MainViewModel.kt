package com.example.filmsearch.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmsearch.api.RemoteDataSource
import com.example.filmsearch.model.TopMoviesResponse
import com.example.filmsearch.presentation.ScreenState
import com.example.filmsearch.repository.movie.Repository
import com.example.filmsearch.repository.movie.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

private const val SERVER_ERROR = "Ошибка сервера"
private const val CORRUPTED_DATA = "Неполные данные"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"

class MainViewModel(
    private var liveDataToObserve: MutableLiveData<ScreenState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl(RemoteDataSource())
) : ViewModel() {

    //здесь обрабатывается полученный ответ от сервера и принимается решение о состоянии экрана
    private val callBack = object : Callback<TopMoviesResponse> {
        @Throws(IOException::class)
        // Вызывается, если ответ от сервера пришёл(даже пустой или с ошибкой)
        override fun onResponse(
            call: Call<TopMoviesResponse>,
            response: Response<TopMoviesResponse>
        ) {
            val serverResponse: TopMoviesResponse? = response.body()
            liveDataToObserve.postValue(
                // Синхронизируем поток с потоком UI
                //  isSuccessful - если ответ удачный от 200 до 300 не включая
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    ScreenState.Error(Throwable(SERVER_ERROR))
                }
            )
        }
        //проверяем ответ
        private fun checkResponse(serverResponse: TopMoviesResponse): ScreenState {
            val result = serverResponse.items
            //возвращаем или ошибку, или успех
            return if (result.isEmpty()) {
                ScreenState.Error(Throwable(CORRUPTED_DATA))
            } else {
                ScreenState.Success(serverResponse.toDomain())
            }
        }

        override fun onFailure(call: Call<TopMoviesResponse>, t: Throwable) {
            liveDataToObserve.postValue(
                ScreenState.Error(
                    Throwable(t.message ?: REQUEST_ERROR)
                )
            )        }
    }

    fun requestLiveData(): LiveData<ScreenState> {
        return liveDataToObserve
    }

    fun loadMovies() {
        liveDataToObserve.value = ScreenState.Loading
        repository.getMovies(callBack)
    }
}