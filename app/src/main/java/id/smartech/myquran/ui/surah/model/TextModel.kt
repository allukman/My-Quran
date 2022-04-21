package id.smartech.myquran.ui.surah.model

data class TextModel(
    val arab: String?,
    val transliteration: Transliteration?
) {
    data class Transliteration(
        val en: String?
    )
}
