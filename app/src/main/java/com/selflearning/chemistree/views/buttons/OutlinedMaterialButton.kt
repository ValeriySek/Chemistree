package com.selflearning.chemistree.views.buttons

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.view.updatePadding
import com.google.android.material.button.MaterialButton
import com.selflearning.chemistree.R
import com.selflearning.chemistree.utils.extentions.dpToPx

class OutlinedMaterialButton(
    context: Context
) : MaterialButton(context, null, R.attr.materialButtonOutlinedStyle) {

    init {
        isAllCaps = false
        cornerRadius = dpToPx(24)
        strokeColor = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_pressed, android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_enabled),
            ),
            intArrayOf(
                Color.parseColor("#E74A19"),
                Color.parseColor("#E74A19"),
                Color.parseColor("#B7AFAF")
            )
        )
        insetBottom = 0
        insetTop = 0

        minimumWidth = dpToPx(0) // оба нужны чтобы
        minWidth = dpToPx(0)     // уменьшить ширину кнопки

        minHeight = dpToPx(0)
        minimumHeight = dpToPx(0)

        updatePadding(
            left = dpToPx(12),
            top = dpToPx(8),
            right = dpToPx(12),
            bottom = dpToPx(8)
        )
        stateChanged(isEnabled)
    }

    fun stateChanged(isEnabled: Boolean) {
        val textStyle = if (isEnabled)
            R.style.Text_Regular_12_PrimaryColor
        else
            R.style.Text_Regular_12_SecondaryColor
        setTextAppearance(textStyle)
    }
}