package id.smartech.myquran.ui.main.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AllSurahResponse (
    val code: Int,
    val status: String,
    val message: String,
    val data: ArrayList<SurahModel>
)