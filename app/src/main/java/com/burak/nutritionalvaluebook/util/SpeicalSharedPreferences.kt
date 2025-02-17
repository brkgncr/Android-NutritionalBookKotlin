package com.burak.nutritionalvaluebook.util

import android.content.Context
import android.content.SharedPreferences

class SpeicalSharedPreferences {

    companion object {

        private val TIME = "time"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile
        private var instance: SpeicalSharedPreferences? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: specialSharedPreferencesCreate(context).also {
                instance = it
            }
        }

        private fun specialSharedPreferencesCreate(context: Context): SpeicalSharedPreferences {
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return SpeicalSharedPreferences()
        }

        fun timeSave(time : Long) {
            sharedPreferences?.edit()?.putLong(TIME,time)?.apply()
        }

        fun timeCapture() = sharedPreferences?.getLong(TIME,0)

    }
}