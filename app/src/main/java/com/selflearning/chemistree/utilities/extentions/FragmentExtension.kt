package com.selflearning.chemistree.utilities.extentions

import androidx.fragment.app.Fragment

fun Fragment.dpi(): Float{
    return this.resources.displayMetrics.density
}