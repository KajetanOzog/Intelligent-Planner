package com.example.io_project.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Settings(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Settings")
        val SHOW_DAY_SUMMARY = booleanPreferencesKey("show_day_summary")
        val SHOW_OTHER_EVENTS = booleanPreferencesKey("show_other_events")
        val FIRST_VISIT_TODAY = booleanPreferencesKey("first_visit_today")
    }

    val getSummarySettings: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[SHOW_DAY_SUMMARY] ?: false
        }

    suspend fun saveSummarySettings(boolean: Boolean) {
        context.dataStore.edit {preferences ->
            preferences[SHOW_DAY_SUMMARY] = boolean
        }
    }

    val getEventSettings: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[SHOW_OTHER_EVENTS] ?: false
        }

    suspend fun saveEventSettings(boolean: Boolean) {
        context.dataStore.edit {preferences ->
            preferences[SHOW_OTHER_EVENTS] = boolean
        }
    }

    val getFirstVisitBoolean: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[FIRST_VISIT_TODAY] ?: false
        }

    suspend fun saveFirstVisitBoolean(boolean: Boolean) {
        context.dataStore.edit {preferences ->
            preferences[FIRST_VISIT_TODAY] = boolean
        }
    }
}