package id.smartech.myquran.util

import android.os.Build
import android.text.Html
import android.text.Spanned
import java.text.SimpleDateFormat
import java.util.*

object Helper {

    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd MMMM yyyy")
        return sdf.format(Date())
    }

    fun getCurrentDateAdzan(): String {
        val sdf = SimpleDateFormat("yyyy/MM/dd")
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
            Calendar.SATURDAY -> {
                return "Sabtu"
            } else -> {
                return " "
            }
        }
    }

    fun String.fromHtml(): Spanned? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(this)
        }
    }
}
