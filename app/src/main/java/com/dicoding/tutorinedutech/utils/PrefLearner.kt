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

val Context.learnerDataStore: DataStore<Preferences> by preferencesDataStore(name = "learner")

class PrefLearner private constructor(private val learnerDataStore: DataStore<Preferences>) {
    private val USER_TOKEN = stringPreferencesKey("user_token")

    suspend fun setUserToken(token: String) {
        learnerDataStore.edit { mutablePreferences: MutablePreferences ->
            mutablePreferences[USER_TOKEN] = token
        }
    }

    fun getUserToken(): Flow<String?> {
        return learnerDataStore.data.map { value: Preferences -> value[USER_TOKEN] }
    }

    suspend fun clearUserToken() {
        learnerDataStore.edit { mutablePreferences: MutablePreferences ->
            mutablePreferences.remove(USER_TOKEN)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PrefLearner? = null

        fun getInstance(learnerDataStore: DataStore<Preferences>): PrefLearner {
            return INSTANCE ?: synchronized(this) {
                val instance = PrefLearner(learnerDataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}