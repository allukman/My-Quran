package id.smartech.myquran.ui.asmaulhusna.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.smartech.myquran.net.ApiService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AsmaulHusnaViewModel: ViewModel(), CoroutineScope {
    private lateinit var services: ApiService

    val onSuccessLiveData = MutableLiveData<List<AsmaulHusnaModel>>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setService(services: ApiService) {
        this.services = services
    }

    fun getAsmaulHusna() {
        launch {
            isLoadingLiveData.value = false
            val response = services.getAsmaulHusna()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    onSuccessLiveData.value = response.body()
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