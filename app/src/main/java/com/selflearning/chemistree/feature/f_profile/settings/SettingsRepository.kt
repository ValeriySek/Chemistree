package com.selflearning.chemistree.feature.f_profile.settings

import android.content.Context
import android.util.Log
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsData
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsItemType
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsItemType.*
import com.selflearning.chemistree.utils.daily_notifications.RemindersManager
import com.selflearning.chemistree.utils.storage.PreferenceManager
import com.selflearning.chemistree.utils.storage.PreferenceManager.Settings.PREF_DAILY_NOTIFICATION
import com.selflearning.chemistree.utils.storage.PreferenceManager.Settings.PREF_DAILY_NOTIFICATION_TIME
import com.selflearning.chemistree.utils.storage.PreferenceManager.Settings.PREF_MENDELEEV_TABLE_DESIGN
import com.selflearning.chemistree.utils.storage.PreferenceManager.Settings.PREF_MENDELEEV_TABLE_TYPE
import com.selflearning.chemistree.utils.storage.PreferenceManager.Settings.PREF_PUSH_NOTIFICATION
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    val preferenceManager: PreferenceManager,
    val context: Context
) {


    suspend fun savePUSH_NOTIFICATIONS(value: Boolean) {
        Log.i("TAGGG", "savePUSH_NOTIFICATIONS value $value")
        preferenceManager.preferencesKey(PREF_PUSH_NOTIFICATION, value)
    }

    suspend fun saveDAILY_NOTIFICATIONSS(value: Boolean) {
        Log.i("TAGGG", "saveDAILY_NOTIFICATIONSS value $value")
        preferenceManager.preferencesKey(PREF_DAILY_NOTIFICATION, value as Boolean)
        if(value) {
            RemindersManager.startReminder(context)
        } else {
            RemindersManager.stopReminder(context)
        }
    }

    suspend fun saveTIME_OF_NOTIFICATION(value: String) {
        preferenceManager.preferencesKey(PREF_DAILY_NOTIFICATION_TIME, value)
        RemindersManager.startReminder(context)
    }

    suspend fun saveMENDELEEV_TABLE_TYPE(value: Int) {
        Log.i("TAGGG", "saveMENDELEEV_TABLE_TYPE value $value")
        preferenceManager.preferencesKey(PREF_MENDELEEV_TABLE_TYPE, value as Int)
    }

    suspend fun saveMENDELEEV_TABLE_DESIGN(value: Int) {
        Log.i("TAGGG", "saveMENDELEEV_TABLE_DESIGN value $value")
        preferenceManager.preferencesKey(PREF_MENDELEEV_TABLE_DESIGN, value as Int)
    }

    suspend fun editPreference(itemType: SettingsItemType, value: Any) {
        when (itemType) {
            PUSH_NOTIFICATIONS -> {
                preferenceManager.preferencesKey(PREF_PUSH_NOTIFICATION, value as Boolean)
            }
            DAILY_NOTIFICATIONS -> {
                preferenceManager.preferencesKey(PREF_DAILY_NOTIFICATION, value as Boolean)
            }
            TIME_OF_NOTIFICATION -> {
                preferenceManager.preferencesKey(PREF_DAILY_NOTIFICATION_TIME, value as String)
            }
            MENDELEEV_TABLE_TYPE -> {
                preferenceManager.preferencesKey(PREF_MENDELEEV_TABLE_TYPE, value as Int)
            }
            MENDELEEV_TABLE_DESIGN -> {
                preferenceManager.preferencesKey(PREF_MENDELEEV_TABLE_DESIGN, value as Int)
            }
            else -> { /*nothing*/ }
        }
    }

    fun getSettings(): Flow<SettingsData> {
        return preferenceManager.getSettings()
    }
}