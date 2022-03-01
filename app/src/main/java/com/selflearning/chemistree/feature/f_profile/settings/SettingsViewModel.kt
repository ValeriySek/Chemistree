package com.selflearning.chemistree.feature.f_profile.settings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsData
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsItem
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsItemType
import com.selflearning.chemistree.utils.daily_notifications.RemindersManager
import com.selflearning.chemistree.views.rv_items.data.ButtonType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingsViewModel(
    val repository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<FirstOpenItems>()
    var uiState: LiveData<FirstOpenItems> = _uiState

    private var isReceived: Boolean = false

    init {
        viewModelScope.launch {
            initUi()
        }
    }

    private suspend fun initUi() {
        repository.getSettings().collect {
            initUi(it)
        }
    }

    private fun initUi(settings: SettingsData) {
        val ui = FirstOpenItems(
            isReceived,
            createList(settings)
        )
        _uiState.postValue(ui)
        isReceived = true
    }

    private fun createList(settings: SettingsData): List<SettingsItem> {
        return listOf(
            SettingsItem.Title("Уведомления"),
            SettingsItem.ItemList(
                itemType = SettingsItemType.PUSH_NOTIFICATIONS,
                buttonType = ButtonType.SwitchButton,
                text = "Push-уведомления",
                isChecked = settings.pushNotification
            ),
            SettingsItem.ItemList(
                itemType = SettingsItemType.DAILY_NOTIFICATIONS,
                buttonType = ButtonType.SwitchButton,
                text = "Ежедневные напоминания",
                isChecked = settings.dailyNotification
            ),
            SettingsItem.ItemList(
                itemType = SettingsItemType.TIME_OF_NOTIFICATION,
                buttonType = ButtonType.MaterialButton,
                text = "Время напоминания",
                buttonText = settings.dailyNotificationTime,
                isEnabled = settings.dailyNotification
            ),

            SettingsItem.Title("Таблица Менделеева"),
            SettingsItem.ItemList(
                itemType = SettingsItemType.MENDELEEV_TABLE_TYPE,
                buttonType = ButtonType.MaterialButton,
                text = "Форма таблицы",
                buttonText = if (settings.mendeleevTableType == 0) "Длинная" else "Короткая",
            ),
            SettingsItem.ItemList(
                itemType = SettingsItemType.MENDELEEV_TABLE_DESIGN,
                buttonType = ButtonType.NoneButton,
                text = "Дизайн таблицы",
                buttonText = "${settings.mendeleevTableDesign}"
            ),

            SettingsItem.Title("Связь"),
            SettingsItem.ItemList(
                itemType = SettingsItemType.CONTACT_US,
                buttonType = ButtonType.NoneButton,
                text = "Напишите нам",
            ),
            SettingsItem.ItemList(
                itemType = SettingsItemType.ABOUT,
                buttonType = ButtonType.NoneButton,
                text = "О приложении",
            )
        )
    }

    fun saveB(itemType: SettingsItemType, value: Boolean) {
        viewModelScope.launch {
            if(itemType == SettingsItemType.PUSH_NOTIFICATIONS)
                repository.savePUSH_NOTIFICATIONS(value)
            else
                repository.saveDAILY_NOTIFICATIONSS(value)
        }
    }

    fun saveTime(time: String) {
        viewModelScope.launch {
                repository.saveTIME_OF_NOTIFICATION(time)
        }
    }

    data class FirstOpenItems(
        val isReceived: Boolean = false,
        val list: List<SettingsItem>
    )
}