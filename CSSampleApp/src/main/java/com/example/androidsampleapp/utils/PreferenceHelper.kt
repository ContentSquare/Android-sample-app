package com.example.androidsampleapp.utils

import android.content.Context

class PreferenceHelper(val context: Context) {

    companion object {
        private const val SAMPLE_APP_PREFERENCES = "SAMPLE_APP_PREFERENCES"
    }

    private val defaultPreferences =
        context.getSharedPreferences(SAMPLE_APP_PREFERENCES, Context.MODE_PRIVATE)

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return defaultPreferences.getBoolean(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        defaultPreferences.edit().putBoolean(key, value).apply()
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return defaultPreferences.getLong(key, defaultValue)
    }

    fun putLong(key: String, value: Long) {
        defaultPreferences.edit().putLong(key, value).apply()
    }
}
