package id.smartech.myquran.util

import java.text.SimpleDateFormat
import java.util.*

object Helper {

    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd MMMM yyyy")
        return sdf.format(Date())
    }

    fun getCurrentDayOfWeek() : String {
        val calendar = Calendar.getInstance()
        when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> {
                return "Minggu"
            }
            Calendar.MONDAY -> {
                return "Senin"
            }
            Calendar.TUESDAY -> {
                return "Selasa"
            }
            Calendar.WEDNESDAY -> {
                return "Rabu"
            }
            Calendar.THURSDAY -> {
                return "Kamis"
            }
            Calendar.FRIDAY -> {
                return "Jum'at"
            }
            Calendar.FRIDAY -> {
                return "Sabtu"
            } else -> {
                return " "
            }
        }
    }
}
