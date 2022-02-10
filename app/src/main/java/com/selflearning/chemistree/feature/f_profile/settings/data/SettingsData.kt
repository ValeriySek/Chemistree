package com.selflearning.chemistree.feature.f_profile.settings.data

data class SettingsData(
    val pushNotification: Boolean = false,
    val dailyNotification: Boolean = false,
    val dailyNotificationTime: String = "",
    val mendeleevTableType: Int = 0,
    val mendeleevTableDesign: Int = 0
)
