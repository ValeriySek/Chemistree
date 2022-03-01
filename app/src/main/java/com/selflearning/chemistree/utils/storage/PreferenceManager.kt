package com.selflearning.chemistree.utils.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("key_settings")

class PreferenceManager
@Inject constructor(
//    private val dataStore: DataStore<Preferences>
val context: Context
)
{

    suspend fun <T> preferencesKey(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { settings ->
            settings[key] = value
        }
    }

    @JvmName("getPreferenceString")
    fun getPreference(key: Preferences.Key<String>): Flow<String> {
        return context.dataStore.data
            .map { preferences ->
                preferences[key] ?: ""
            }
    }

    fun getSettings(): Flow<SettingsData> {
        return context.dataStore.data.map { settings ->
            SettingsData(
                pushNotification = settings[Settings.PREF_PUSH_NOTIFICATION] ?: true,
                dailyNotification = settings[Settings.PREF_DAILY_NOTIFICATION] ?: true,
                dailyNotificationTime = settings[Settings.PREF_DAILY_NOTIFICATION_TIME] ?: "10:00",
                mendeleevTableType = settings[Settings.PREF_MENDELEEV_TABLE_TYPE] ?: 0,
                mendeleevTableDesign = settings[Settings.PREF_MENDELEEV_TABLE_DESIGN] ?: 0,
            )
        }
    }

    fun getDailyNotificationTime(): String {
        return runBlocking {
            context.dataStore.data.map { settings ->
                settings[Settings.PREF_DAILY_NOTIFICATION_TIME] ?: "10:00"
            }.first()
        }
    }

    object Settings {
        val PREF_PUSH_NOTIFICATION = booleanPreferencesKey("key_settings_push_notification")
        val PREF_DAILY_NOTIFICATION = booleanPreferencesKey("key_settings_daily_notification")
        val PREF_DAILY_NOTIFICATION_TIME =
            stringPreferencesKey("key_settings_daily_notification_time")
        val PREF_MENDELEEV_TABLE_TYPE = intPreferencesKey("key_settings_mendeleev_table_type")
        val PREF_MENDELEEV_TABLE_DESIGN = intPreferencesKey("key_settings_mendeleev_table_design")
    }
}