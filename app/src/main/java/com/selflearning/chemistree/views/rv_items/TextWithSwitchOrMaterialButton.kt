package com.selflearning.chemistree.views.rv_items

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import com.google.android.material.switchmaterial.SwitchMaterial
import com.selflearning.chemistree.R
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsItemType
import com.selflearning.chemistree.utils.extentions.wrapContent
import com.selflearning.chemistree.views.buttons.OutlinedMaterialButton
import com.selflearning.chemistree.views.rv_items.data.ButtonType

class TextWithSwitchOrMaterialButton(
    context: Context
) : LinearLayout(context) {

    private val textView = TextView(context)
    private val materialButton = OutlinedMaterialButton(context)
    private val switchButton =
        SwitchMaterial(ContextThemeWrapper(context, R.style.Widget_App_Switch))

    var text = "Text"
        set(value) {
            textView.text = value
            field = value
        }

    fun initView(
        text: String = "",
        isChecked: Boolean = false,
        onButtonClick: (SettingsItemType) -> Unit,
        onItemClick: (SettingsItemType) -> Unit,
        onSwitchClick: (SettingsItemType, Boolean) -> Unit,
        buttonType: ButtonType,
        itemType: SettingsItemType,
        isEnabled: Boolean = true
    ) {
        removeView(materialButton)
        removeView(switchButton)

        enabled(isEnabled)
        materialButton.apply {
            this.text = text
            setOnClickListener { onButtonClick(itemType) }
        }
        if (switchButton.isChecked != isChecked)
            switchButton.isChecked = isChecked
        setOnClickListener { onItemClick(itemType) }
        switchButton.setOnCheckedChangeListener { _, checked ->
            onSwitchClick(itemType, checked)
        }
        when (buttonType) {
            ButtonType.MaterialButton -> {
                addView(
                    materialButton,
                    wrapContent(Gravity.CENTER_VERTICAL)
                )
            }
            ButtonType.SwitchButton -> {
                addView(
                    switchButton,
                    wrapContent(Gravity.CENTER_VERTICAL)
                )
            }
            ButtonType.NoneButton -> { /* do nothing*/ }
        }
    }

    fun enabled(enabled: Boolean) {
        materialButton.isEnabled = enabled
        materialButton.stateChanged(enabled)
        textView.isEnabled = enabled

        val textStyle = if (enabled)
            R.style.Text_Regular_12_PrimaryColor
        else
            R.style.Text_Regular_12_SecondaryColor
        textView.setTextAppearance(textStyle)
    }

    init {
        orientation = HORIZONTAL
        isClickable = true
        isFocusable = true
        val tv = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, tv, true)
        setBackgroundResource(tv.resourceId)

        addView(textView, wrapContent(Gravity.CENTER_VERTICAL, 1f))
    }
}