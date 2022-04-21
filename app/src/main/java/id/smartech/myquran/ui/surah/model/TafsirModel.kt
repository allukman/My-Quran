package id.smartech.myquran.ui.surah.model

data class TafsirModel (
    val id: OptionModel
) {
    data class OptionModel(
        val short: String?,
        val long: String?
    )
}