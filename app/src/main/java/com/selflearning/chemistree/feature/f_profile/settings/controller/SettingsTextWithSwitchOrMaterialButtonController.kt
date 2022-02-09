package com.selflearning.chemistree.feature.f_profile.settings.controller

import android.view.ViewGroup
import androidx.core.view.updatePadding
import com.selflearning.chemistree.core.adapter.BindableItemController
import com.selflearning.chemistree.core.adapter.BindableViewHolder
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsItem
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsItemType
import com.selflearning.chemistree.utils.extentions.dpToPx
import com.selflearning.chemistree.utils.extentions.heightWrapWidthMatchContent
import com.selflearning.chemistree.views.rv_items.TextWithSwitchOrMaterialButton

class SettingsTextWithSwitchOrMaterialButtonController(
    val onButtonClick: (SettingsItemType) -> Unit,
    val onItemClick: (SettingsItemType) -> Unit,
    val onSwitchClick: (SettingsItemType, Boolean) -> Unit
) : BindableItemController() {

    override fun createViewHolder(parent: ViewGroup): BindableViewHolder {
        val tv = TextWithSwitchOrMaterialButton(parent.context).apply {
            layoutParams = heightWrapWidthMatchContent()
            minimumHeight = dpToPx(48)
            updatePadding(
                left = dpToPx(32),
                top = dpToPx(4),
                right = dpToPx(24),
                bottom = dpToPx(4)
            )
        }
        return Holder(tv)
    }

    inner class Holder(val view: TextWithSwitchOrMaterialButton) : BindableViewHolder(view) {

        override fun bind(data: Any) {
            val item = data as SettingsItem.ItemList

            view.text = item.text
            view.initView(
                item.buttonText,
                true,
                onButtonClick,
                onItemClick,
                onSwitchClick,
                item.buttonType,
                item.itemType
            )
        }
    }
}