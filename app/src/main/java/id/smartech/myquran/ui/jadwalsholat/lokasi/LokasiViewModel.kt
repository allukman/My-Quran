package id.smartech.myquran.ui.jadwalsholat.lokasi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.smartech.myquran.net.ApiService
import id.smartech.myquran.ui.jadwalsholat.lokasi.model.LocationModel
import id.smartech.myquran.ui.jadwalsholat.lokasi.model.LocationResponse
import id.smartech.myquran.util.KarsaLogger
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LokasiViewModel: ViewModel(), CoroutineScope {
    private lateinit var services: ApiService

    val onSuccessLiveData = MutableLiveData<List<LocationModel>>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val isNotFound = MutableLiveData<Boolean>()

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setService(services: ApiService) {
        this.services = services
    }

    fun getLocation(query: String) {
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    services.getCityLocation(search = query)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main) {
                    isLoadingLiveData.value = false
                    }
                }
            }
            if (response is LocationResponse) onSuccess(response.data)

        }
    }

    private fun onSuccess(result: ArrayList<LocationModel>) {
        onSuccessLiveData.value = result
        isLoadingLiveData.value = false
    }
}