package com.selflearning.chemistree.utils.extentions

import com.selflearning.chemistree.R

fun Int.string() =
    when (this) {
        1 -> R.string.one
        2 -> R.string.two
        3 -> R.string.three
        4 -> R.string.four
        5 -> R.string.five
        6 -> R.string.six
        7 -> R.string.seven
        8 -> R.string.eight
        9 -> R.string.nine
        10 -> R.string.ten
        else -> error("unknown number")
    }


