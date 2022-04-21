package id.smartech.myquran.ui.main.model

import java.io.Serializable

data class SurahNameModel(
    val short: String?,
    val long: String?,
    val transliteration: NameTranslationModel?,
    val translation: NameTranslationModel?
) : Serializable {
    data class NameTranslationModel(
        val en: String?,
        val id: String?
    ) : Serializable
}
