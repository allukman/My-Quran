package id.smartech.myquran.util

import android.util.Log

object KarsaLogger {
    fun print(message: String?) {
        print("KARSADEBUG", message)
    }

    fun print(tag: String?, message: String?) {
        Log.d("KARSADEBUG", message!!)
    }
}