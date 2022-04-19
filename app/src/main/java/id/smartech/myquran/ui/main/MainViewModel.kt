package id.smartech.myquran.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.smartech.myquran.net.ApiService
import id.smartech.myquran.ui.main.model.ListSurahModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel(), CoroutineScope {
    private lateinit var services: ApiService

    val onSuccessLiveData = MutableLiveData<List<ListSurahModel>>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setService(services: ApiService) {
        this.services = services
    }

    fun getListSurah() {
        launch {
            isLoadingLiveData.value = true
            val response = services.getListSurah()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    onSuccessLiveData.postValue(response.body())
                    isLoadingLiveData.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        isLoadingLiveData.value = false
    }
}