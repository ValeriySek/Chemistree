package com.selflearning.chemistree.feature.f_profile.settings.data

import com.selflearning.chemistree.R
import com.selflearning.chemistree.views.rv_items.data.ButtonType

sealed class SettingsItem {

    data class Title(val title: String) : SettingsItem()

    data class ItemList(
        val itemType: SettingsItemType,
        val buttonType: ButtonType,
        val text: String,
        val buttonText: String = "",
        val textStyle: Int = R.style.Text_Regular_12_PrimaryColor,
        val buttonTextStyle: Int = R.style.Text_Regular_12_PrimaryColor,
        val isEnabled: Boolean = true,
        val isChecked: Boolean = false
    ) : SettingsItem()
}
