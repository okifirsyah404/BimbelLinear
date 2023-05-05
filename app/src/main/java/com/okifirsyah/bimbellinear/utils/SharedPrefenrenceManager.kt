package com.okifirsyah.bimbellinear.utils

import android.content.Context

class SharedPrefenrenceManager(context: Context) {
    private var prefs = context.getSharedPreferences("bimbel_linear", Context.MODE_PRIVATE)
    private var editor = prefs.edit()

    fun setStringPreference(prefKey: String, value: String) {
        editor.putString(prefKey, value)
        editor.apply()
    }

    fun setBooleanPreference(prefKey: String, value: Boolean) {
        editor.putBoolean(prefKey, value)
        editor.apply()
    }

    fun clearPreferenceByKey(prefKey: String) {
        editor.remove(prefKey)
        editor.apply()
    }

    val getToken = prefs.getString("token", "")
    val getEmail = prefs.getString("email", "")
    val isAlreadyIntroduced = prefs.getBoolean("is_already_introduced", false)

    companion object {
        @Volatile
        private var INSTANCE: SharedPrefenrenceManager? = null

        fun getInstance(context: Context): SharedPrefenrenceManager {
            return INSTANCE ?: synchronized(this) {
                val instance = SharedPrefenrenceManager(context)
                INSTANCE = instance
                instance
            }
        }
    }

}