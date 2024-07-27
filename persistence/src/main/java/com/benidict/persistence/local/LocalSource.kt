package com.benidict.persistence.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class LocalSource @Inject constructor(
    private val context: Context
) {
    companion object {
        private const val IS_LOGGED_IN = "IS_LOGGED_IN"
    }

    private val pref = context.getSharedPreferences("77_pref", Context.MODE_PRIVATE)

    var loggedIn: Boolean
        get() = pref.getBoolean(IS_LOGGED_IN, false)
        set(value) = pref.edit().putBoolean(IS_LOGGED_IN, value).apply()
}