package com.selflearning.chemistree.utils.extentions

import androidx.fragment.app.Fragment

fun Fragment.dpi(): Float{
    return this.resources.displayMetrics.density
}