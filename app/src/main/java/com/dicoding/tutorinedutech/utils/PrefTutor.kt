package com.dicoding.tutorinedutech.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.tutorDataStore: DataStore<Preferences> by preferencesDataStore(name = "tutor")

class PrefTutor private constructor(private val tutorDataStore: DataStore<Preferences>) {
    private val USER_TOKEN = stringPreferencesKey("user_token")

    suspend fun setUserToken(token: String) {
        tutorDataStore.edit { mutablePreferences: MutablePreferences ->
            mutablePreferences[USER_TOKEN] = token
        }
    }

    fun getUserToken(): Flow<String?> {
        return tutorDataStore.data.map { value: Preferences -> value[USER_TOKEN] }
    }

    suspend fun clearUserToken() {
        tutorDataStore.edit { mutablePreferences: MutablePreferences ->
            mutablePreferences.remove(USER_TOKEN)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PrefTutor? = null

        fun getInstance(tutorDataStore: DataStore<Preferences>): PrefTutor {
            return INSTANCE ?: synchronized(this) {
                val instance = PrefTutor(tutorDataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}