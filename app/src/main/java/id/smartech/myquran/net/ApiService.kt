package id.smartech.myquran.net

import id.smartech.myquran.ui.asmaulhusna.model.AsmaulHusnaModel
import id.smartech.myquran.ui.kisahnabi.model.KisahNabiModel
import id.smartech.myquran.ui.main.model.AllSurahResponse
import id.smartech.myquran.ui.surah.model.DetailSurahModel
import id.smartech.myquran.ui.surah.model.DetailSurahResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("surah")
    suspend fun getListSurah(): Response<AllSurahResponse>

    @GET("surah/{id}")
    suspend fun getDetailSurah(@Path("id")surahId: String): Response<DetailSurahResponse>

    @GET("mikqi/dzikir-counter/master/www/asmaul-husna.json")
    suspend fun getAsmaulHusna(): Response<List<AsmaulHusnaModel>>

    @GET("kisahnabi")
    suspend fun getKisahNabi(): Response<List<KisahNabiModel>>

}