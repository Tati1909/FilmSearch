package com.example.filmsearch.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmsearch.api.RemoteDataSource
import com.example.filmsearch.model.details.DetailsMovie
import com.example.filmsearch.model.details.DetailsResponse
import com.example.filmsearch.presentation.ScreenState
import com.example.filmsearch.presentation.main.CORRUPTED_DATA
import com.example.filmsearch.presentation.main.REQUEST_ERROR
import com.example.filmsearch.presentation.main.SERVER_ERROR
import com.example.filmsearch.repository.details.DetailsRepository
import com.example.filmsearch.repository.details.DetailsRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class DetailsViewModel(
    private val detailsLiveData: MutableLiveData<ScreenState> = MutableLiveData(),
    private val detailsRepository: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource())
) : ViewModel() {

    //здесь обрабатывается полученный ответ от сервера и принимается решение о состоянии экрана
    private val callBack = object : Callback<DetailsResponse> {
        @Throws(IOException::class)
        // Вызывается, если ответ от сервера пришёл(даже пустой или с ошибкой)
        override fun onResponse(
            call: Call<DetailsResponse>,
            response: Response<DetailsResponse>
        ) {
            val serverResponse: DetailsResponse? = response.body()
            detailsLiveData.postValue(
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
        private fun checkResponse(serverResponse: DetailsResponse): ScreenState {
            //возвращаем или ошибку, или успех
            return if (serverResponse.id.isEmpty() || serverResponse.fullTitle.isEmpty()) {
                ScreenState.Error(Throwable(CORRUPTED_DATA))
            } else {
                ScreenState.Success(serverResponse.toDomain())
            }
        }

        override fun onFailure(call: Call<DetailsResponse>, t: Throwable) {
            detailsLiveData.postValue(
                ScreenState.Error(
                    Throwable(t.message ?: REQUEST_ERROR)
                )
            )
        }
    }

    fun requestLiveData(): LiveData<ScreenState> {
        return detailsLiveData
    }

    fun loadDetailsMovie(titleId: String) {
        detailsLiveData.value = ScreenState.Loading
        detailsRepository.getDetails(callBack, titleId)
    }
}