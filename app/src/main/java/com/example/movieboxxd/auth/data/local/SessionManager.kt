package com.example.movieboxxd.auth.data.local


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

    companion object {
        private val SESSION_ID_KEY = stringPreferencesKey("session_id")
        private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
        private val ACCOUNT_ID_KEY = intPreferencesKey("account_id")
    }

    suspend fun saveSession(sessionId: String) {
        context.dataStore.edit { preferences ->
            preferences[SESSION_ID_KEY] = sessionId
            preferences[IS_LOGGED_IN_KEY] = true
        }
    }

    fun getSessionId(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[SESSION_ID_KEY] ?: ""
        }
    }

    suspend fun saveAccountId(accountId: Int) {
        context.dataStore.edit { preferences ->
            preferences[ACCOUNT_ID_KEY] = accountId
        }
    }

    fun getAccountId(): Flow<Int> {
        return context.dataStore.data.map { preferences ->
            preferences[ACCOUNT_ID_KEY] ?: -1
        }
    }

    fun isLoggedIn(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: false
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}