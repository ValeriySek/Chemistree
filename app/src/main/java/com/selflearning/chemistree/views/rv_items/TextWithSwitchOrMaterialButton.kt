package com.selflearning.chemistree.views.rv_items

import android.content.Context
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

    private val textView = TextView(context).apply {
        setTextAppearance(R.style.Text_Regular_12_PrimaryColor)
    }
    private val materialButton = OutlinedMaterialButton(context)
    private val switchButton = SwitchMaterial(ContextThemeWrapper(context, R.style.Widget_App_Switch))

    var text = "Text"
        set(value) {
            textView.text = value
            field = value
        }

    fun initView(
        text: String = "",
        isEnabled: Boolean = true,
        onButtonClick: (SettingsItemType) -> Unit,
        onItemClick: (SettingsItemType) -> Unit,
        onSwitchClick: (SettingsItemType, Boolean) -> Unit,
        buttonType: ButtonType,
        itemType: SettingsItemType
    ) {
        materialButton.apply {
            this.text = text
            this.isEnabled = isEnabled
            setOnClickListener { onButtonClick(itemType) }
        }
        setOnClickListener { onItemClick(itemType) }
        switchButton.setOnCheckedChangeListener { _, isChecked ->
            onSwitchClick(itemType, isChecked)
        }
        when (buttonType) {
            is ButtonType.MaterialButton -> addView(
                materialButton,
                wrapContent(Gravity.CENTER_VERTICAL)
            )
            is ButtonType.SwitchButton -> addView(switchButton,
                wrapContent(Gravity.CENTER_VERTICAL)
            )
            is ButtonType.NoneButton -> { /* do nothing*/ }
        }
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