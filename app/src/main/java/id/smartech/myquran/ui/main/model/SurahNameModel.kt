package id.smartech.myquran.ui.main.model

data class SurahNameModel(
    val short: String?,
    val long: String?,
    val transliteration: NameTranslationModel?,
    val translation: NameTranslationModel?
) {
    data class NameTranslationModel(
        val en: String?,
        val id: String?
    )
}
