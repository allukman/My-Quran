package id.smartech.myquran.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(private val context: Context) {
    companion object {
        const val PREF_NAME = "ORDER_AND_GO"

        const val LOCATION_ID = "LOCATION_ID"
    }
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

//  Save Data

    fun saveLocationId(id: String) {
        editor.putString(LOCATION_ID, id)
        editor.commit()
    }

    val locationId by lazy {
        sharedPreferences.getString(LOCATION_ID, null)
    }

//  Clear

    fun accountLogout() {
        editor.clear()
        editor.commit()
    }
}