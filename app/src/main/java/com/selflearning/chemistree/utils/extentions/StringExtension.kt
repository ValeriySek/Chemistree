package com.selflearning.chemistree.utils.extentions

import android.text.Html
import android.text.Spanned
import com.selflearning.chemistree.utils.PATTERN_FOR_MOLECULE
import com.selflearning.chemistree.utils.PATTERN_FOR_REACTION
import java.util.*

fun String.replaceOnSubstringSpanned(): Spanned {
    return Html.fromHtml(this.replaceOnSubstringString())
}

fun String.replaceOnSubstringString(): String {
    return this.replace("([2-9]*)".toRegex(), PATTERN_FOR_MOLECULE)
}

fun String.rep(): String {
    return replace("([a-zA-Z])([0-9]*)".toRegex(), PATTERN_FOR_REACTION)
}

fun String.getCationName(): String {
    return if(Locale.getDefault().language == Locale("en").language) {
        this.replace("\\s[a-zA-Z]*".toRegex(), "")
    } else {
        this.replace("[а-яА-Я]*\\s".toRegex(), "")
    }
}

fun String.notDigit(): Boolean = toIntOrNull() == null

fun String.allLetters(): Boolean = toCharArray().all { it.isLetter() }
