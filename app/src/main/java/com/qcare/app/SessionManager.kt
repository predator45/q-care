package com.qcare.app

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences("qcare_session", Context.MODE_PRIVATE)

    fun saveLogin(email: String, role: String) {
        pref.edit()
            .putBoolean("is_login", true)
            .putString("email", email)
            .apply()
    }

    fun isLogin(): Boolean = pref.getBoolean("is_login", false)

    fun getEmail(): String? = pref.getString("email", null)

    fun logout() {
        pref.edit().clear().apply()
    }
}