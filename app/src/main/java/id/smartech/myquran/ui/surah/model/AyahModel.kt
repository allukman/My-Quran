package id.smartech.myquran.ui.surah.model

data class AyahModel(
    val number: NumberModel?,
    val text: TextModel?,
    val translation: TranslationModel?,
    val audio : AudioModel?,
    val tafsir: TafsirModel?
)
