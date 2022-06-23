package id.smartech.myquran.net

import id.smartech.myquran.ui.asmaulhusna.model.AsmaulHusnaModel
import id.smartech.myquran.ui.kisahnabi.model.KisahNabiModel
import id.smartech.myquran.ui.jadwalsholat.lokasi.model.LocationResponse
import id.smartech.myquran.ui.jadwalsholat.model.JadwalSholatResponse
import id.smartech.myquran.ui.main.model.AllSurahResponse
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

    @GET("sholat/kota/cari/{search}")
    suspend fun getCityLocation(@Path("search")search: String): LocationResponse

    @GET("https://api.myquran.com/v1/sholat/jadwal/{location}/{year}/{month}/{day}")
    suspend fun getAdzanByCity(
        @Path("location")location: String,
        @Path("year")year: String,
        @Path("month")month: String,
        @Path("day")day: String
    ): JadwalSholatResponse

}