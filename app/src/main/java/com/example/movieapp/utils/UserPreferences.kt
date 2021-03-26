package com.example.movieapp.utils

import android.content.Context

class UserPreferences (context: Context) {

    companion object {
        private const val PREF_NAME = "pref_name"
        private const val STATUS_USER = "status_user"
        private const val NAMA_USER = "nama_user"
    }

    private val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setStatusUser (boolean: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(STATUS_USER, boolean)
        editor.apply()
    }

    fun getStatusUser (): Boolean {
        return preferences.getBoolean(STATUS_USER, false)
    }

    fun setNameUser (string: String) {
        val editor = preferences.edit()
        editor.putString(NAMA_USER, string)
        editor.apply()
    }

    fun getNameUser (): String? {
        return preferences.getString(NAMA_USER, "")
    }

    fun deleteAllData () {
        val delete = preferences.edit().clear().apply()
    }

}