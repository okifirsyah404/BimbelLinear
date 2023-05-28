package com.okifirsyah.bimbellinear.data.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPreferences(private val dataStore: DataStore<Preferences>) {

    private val authTokenKey = stringPreferencesKey("auth_token")
    private val themeKey = booleanPreferencesKey("theme_setting")
    private val firstLaunchKey = booleanPreferencesKey("first_launch")
    private val changePasswordKey = booleanPreferencesKey("change_password")

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[themeKey] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[themeKey] = isDarkModeActive
        }
    }

    fun getAuthToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[authTokenKey] ?: ""
        }
    }

    suspend fun saveAuthToken(authToken: String) {
        dataStore.edit { preferences ->
            preferences[authTokenKey] = authToken
        }
    }

    fun getFirstLaunch(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[firstLaunchKey] ?: true
        }
    }

    suspend fun saveFirstLaunch(isFirstLaunch: Boolean) {
        dataStore.edit { preferences ->
            preferences[firstLaunchKey] = isFirstLaunch
        }
    }

    fun getChangePassword(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[changePasswordKey] ?: false
        }
    }

    suspend fun saveChangePassword(isChangePassword: Boolean) {
        dataStore.edit { preferences ->
            preferences[changePasswordKey] = isChangePassword
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): AppPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = AppPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}