package id.smartech.myquran.net

import id.smartech.myquran.ui.main.model.ListSurahModel
import id.smartech.myquran.ui.surah.model.DetailSurahModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("surat")
    suspend fun getListSurah(): Response<List<ListSurahModel>>

    @GET("surat/{id}")
    suspend fun getDetailSurah(@Path("id")surahId: String): Response<DetailSurahModel>

}