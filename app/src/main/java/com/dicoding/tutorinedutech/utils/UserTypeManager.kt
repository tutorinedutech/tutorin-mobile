package com.dicoding.tutorinedutech.utils

import android.content.Context

object UserTypeManager {
    fun getUserType(context: Context): UserType? {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userType = sharedPreferences.getString("userType", null)
        return userType?.let { UserType.valueOf(it) }
    }

    fun saveUserType(context: Context, userType: UserType) {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userType", userType.name)
        editor.apply()
    }
}