package id.smartech.myquran.ui.surah.model

data class DetailSurahResponse (
    val code: Int,
    val status: String,
    val message: String,
    val data : DetailSurahModel
)