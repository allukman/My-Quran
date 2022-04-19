package id.smartech.myquran.net

import id.smartech.myquran.ui.main.model.ListSurahModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("surat")
    suspend fun getListSurah(): Response<List<ListSurahModel>>

}