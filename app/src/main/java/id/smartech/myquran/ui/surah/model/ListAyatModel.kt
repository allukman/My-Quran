package id.smartech.myquran.ui.surah.model

import com.google.gson.annotations.SerializedName

data class ListAyatModel(
    @SerializedName("id")val id: Int?,
    @SerializedName("surah")val surah: String?,
    @SerializedName("nomor")val nomor: Int?,
    @SerializedName("ar")val ar: String?,
    @SerializedName("tr")val tr: String?,
    @SerializedName("idn")val idn: String
)
