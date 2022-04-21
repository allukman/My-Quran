package id.smartech.myquran.ui.main.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SurahModel (
    val number: Int?,
    val sequence: Int?,
    val numberOfVerses: Int?,
    val name: SurahNameModel?,
    val revelation: RevelationModel?,
    val tafsir: SurahTafsirModel?
) : Serializable