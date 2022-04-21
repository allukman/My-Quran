package id.smartech.myquran.ui.kisahnabi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.smartech.myquran.net.ApiService
import id.smartech.myquran.ui.asmaulhusna.model.AsmaulHusnaModel
import id.smartech.myquran.ui.kisahnabi.model.KisahNabiModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class KisahNabiViewModel: ViewModel(), CoroutineScope {
    private lateinit var services: ApiService

    val onSuccessLiveData = MutableLiveData<List<KisahNabiModel>>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setService(services: ApiService) {
        this.services = services
    }

    fun getKisahNabi() {
        launch {
            isLoadingLiveData.value = true
            val response = services.getKisahNabi()
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