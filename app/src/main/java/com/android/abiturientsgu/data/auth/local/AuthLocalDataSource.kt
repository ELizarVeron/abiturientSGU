package com.android.abiturientsgu.data.auth.local

import android.content.Context
import androidx.appcompat.app.AppCompatActivity


class AuthLocalDataSource(val context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("sp", AppCompatActivity.MODE_PRIVATE)

    fun saveCurUSer(userLogin: String) {
        sharedPreferences.edit().putString("login", userLogin).apply()
    }

    fun getCurUserLogin() {
        sharedPreferences.getString("login", "")
    }

    fun logout() {
        sharedPreferences.edit().putString("login", "").apply()
    }
}

