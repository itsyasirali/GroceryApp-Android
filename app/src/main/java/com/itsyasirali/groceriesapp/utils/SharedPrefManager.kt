package com.itsyasirali.groceriesapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.itsyasirali.groceriesapp.domain.model.User

class SharedPrefManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveUser(user: User) {
        val json = gson.toJson(user)
        prefs.edit().putString(KEY_USER, json).apply()
    }

    fun getUser(): User? {
        val json = prefs.getString(KEY_USER, null)
        return json?.let {
            gson.fromJson(it, User::class.java)
        }
    }

    fun clearUser() {
        prefs.edit().remove(KEY_USER).apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.contains(KEY_USER)
    }

    companion object {
        private const val KEY_USER = "logged_in_user"
    }
}
