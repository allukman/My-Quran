package id.smartech.myquran.ui.jadwalsholat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.smartech.myquran.net.ApiService
import id.smartech.myquran.ui.jadwalsholat.model.JadwalSholatModel
import id.smartech.myquran.ui.jadwalsholat.model.JadwalSholatResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class JadwalSholatViewModel: ViewModel(), CoroutineScope {
    private lateinit var services: ApiService

    val onSuccessLiveData = MutableLiveData<JadwalSholatModel>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val isNotFound = MutableLiveData<Boolean>()

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setService(services: ApiService) {
        this.services = services
    }

    fun getJadwalAdzan(location: String, year: String, month: String, day: String) {
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    services.getAdzanByCity(location, year, month, day)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main) {
                        isLoadingLiveData.value = false
                    }
                }
            }
            if (response is JadwalSholatResponse) onSuccess(response.data)

        }
    }

    private fun onSuccess(result: JadwalSholatModel) {
        onSuccessLiveData.value = result
        isLoadingLiveData.value = false
    }
}