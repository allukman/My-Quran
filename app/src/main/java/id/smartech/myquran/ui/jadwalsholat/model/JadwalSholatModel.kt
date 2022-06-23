package id.smartech.myquran.ui.jadwalsholat.model

data class JadwalSholatModel(
    val id: String?,
    val lokasi: String?,
    val daerah: String?,
    val jadwal: JadwalModel?
) {
    data class JadwalModel(
        val imsak: String?,
        val subuh: String?,
        val dzuhur: String?,
        val ashar: String?,
        val maghrib: String?,
        val isya: String?
    )
}
