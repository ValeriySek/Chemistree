package com.selflearning.chemistree.utils.extentions

import android.content.Context

fun Context.getStringResource(name: String): String {
    return this.resources.getString(this.resources.getIdentifier(name, "string", this.packageName))
}