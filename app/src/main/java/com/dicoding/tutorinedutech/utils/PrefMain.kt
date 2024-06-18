package com.dicoding.tutorinedutech.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.mainDataStore: DataStore<Preferences> by preferencesDataStore(name = "main")

class PrefMain private constructor(private val mainDataStore: DataStore<Preferences>) {
    private val ONBOARDING_STATUS = booleanPreferencesKey("onboarding_status")

    suspend fun setOnboardingStatus(status: Boolean) {
        mainDataStore.edit { mutablePreferences: MutablePreferences ->
            mutablePreferences[ONBOARDING_STATUS] = status
        }
    }

    fun getOnboardingStatus(): Flow<Boolean?> {
        return mainDataStore.data.map { value: Preferences -> value[ONBOARDING_STATUS] }
    }

    companion object {
        @Volatile
        private var INSTANCE: PrefMain? = null

        fun getInstance(mainDataStore: DataStore<Preferences>): PrefMain {
            return INSTANCE ?: synchronized(this) {
                val instance = PrefMain(mainDataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}