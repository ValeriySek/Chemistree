package com.selflearning.chemistree.utils.extentions

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

fun Context.getStringResource(name: String): String {
    return this.resources.getString(this.resources.getIdentifier(name, "string", this.packageName))
}

fun Context.dpToPx(value: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value.toFloat(),
        getDisplayMetrics()
    ).toInt()
}

fun Context.getDisplayMetrics(): DisplayMetrics = resources.displayMetrics