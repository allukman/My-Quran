package id.smartech.myquran.ui.main.model

import com.google.gson.annotations.SerializedName

data class AllSurahResponse (
    val code: Int,
    val status: String,
    val message: String,
    val data: ArrayList<SurahModel>
)