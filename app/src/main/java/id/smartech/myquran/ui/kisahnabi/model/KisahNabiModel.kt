package id.smartech.myquran.ui.kisahnabi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class KisahNabiModel (
    @SerializedName("name")val name: String?,
    @SerializedName("thn_kelahiran")val tahunKelahiran: String?,
    @SerializedName("usia")val usia: String?,
    @SerializedName("description")val desc: String?,
    @SerializedName("tmp")val tmp: String?
) : Serializable