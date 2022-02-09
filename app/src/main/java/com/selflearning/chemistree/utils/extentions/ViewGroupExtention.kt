package com.selflearning.chemistree.utils.extentions

import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout

val ViewGroup.LayoutParams.wrapContent: ViewGroup.LayoutParams
    get() = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

fun ViewGroup.LayoutParams.wrapContent(): ViewGroup.LayoutParams =
    ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)


fun LinearLayout.wrapContent(
    gravity: Int = Gravity.CENTER,
    weight: Float = 0f
): ViewGroup.LayoutParams = LinearLayout.LayoutParams(
    ViewGroup.LayoutParams.WRAP_CONTENT,
    ViewGroup.LayoutParams.WRAP_CONTENT
).apply {
    this.gravity = gravity
    this.weight = weight
}