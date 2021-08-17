package com.selflearning.chemistree.utilities.extentions

import android.content.Context

fun Context.getStringResource(name: String): String {
    return this.resources.getString(this.resources.getIdentifier(name, "string", this.packageName))
}