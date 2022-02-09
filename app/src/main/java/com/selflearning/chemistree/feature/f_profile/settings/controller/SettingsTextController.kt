package com.selflearning.chemistree.feature.f_profile.settings.controller

import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.updatePadding
import com.selflearning.chemistree.R
import com.selflearning.chemistree.core.adapter.BindableItemController
import com.selflearning.chemistree.core.adapter.BindableViewHolder
import com.selflearning.chemistree.utils.extentions.dpToPx
import com.selflearning.chemistree.utils.extentions.wrapContent

class SettingsTextController : BindableItemController() {

    override fun createViewHolder(parent: ViewGroup): BindableViewHolder {
        val tv = TextView(parent.context).apply {
            layoutParams = wrapContent()
            setTextAppearance(R.style.Text_Regular_16_SecondaryColor)
            updatePadding(
                left = dpToPx(24),
                top = dpToPx(16),
                right = dpToPx(24),
                bottom = dpToPx(16)
            )
        }

        return Holder(tv)
    }

    inner class Holder(val view: TextView) : BindableViewHolder(view) {

        override fun bind(data: Any) {
            val text = data as String
            view.paddingBottom
            view.text = text
        }
    }
}