package com.selflearning.chemistree.utils.extentions

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment

fun Fragment.dpi(): Float{
    return this.resources.displayMetrics.density
}