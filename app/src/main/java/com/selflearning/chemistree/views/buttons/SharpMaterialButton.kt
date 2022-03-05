package com.selflearning.chemistree.views.buttons

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.google.android.material.button.MaterialButton
import com.selflearning.chemistree.R
import com.selflearning.chemistree.utils.extentions.dpToPx

class SharpMaterialButton(
    context: Context
) : MaterialButton(context, null, R.attr.materialButtonStyle) {

    init {
        isAllCaps = false
        cornerRadius = dpToPx(0)
        backgroundTintList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_pressed, android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_enabled),
            ),
            intArrayOf(
                Color.parseColor("#333333"),
                Color.parseColor("#000000"),
                Color.parseColor("#B7AFAF")
            )
        )
        insetBottom = 0
        insetTop = 0

        minHeight = dpToPx(52)
        minimumHeight = dpToPx(52)

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
            R.style.Text_Regular_18_White
        else
            R.style.Text_Regular_12_SecondaryColor
        setTextAppearance(textStyle)
    }

    init {
        layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            setMargins(dpToPx(4))
        }
    }
}