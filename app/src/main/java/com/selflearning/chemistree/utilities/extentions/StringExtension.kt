package com.selflearning.chemistree.utilities.extentions

import android.text.Html
import android.text.Spanned

fun String.replaceOnUnderline() : Spanned {
    return Html.fromHtml(this.replace("([2-9]*)".toRegex(), "<sub><small>$1</small></sub>"))
}

fun String.rep() : String {
    return replace("([a-zA-Z])([0-9]*)".toRegex(), "$1<sub><small>$2</small></sub>")
}