package com.selflearning.chemistree.utils

fun sub(group: String): String {
    return "<sub><small>$group</small></sub>"
}

fun sup(group: String): String {
    return "<sup><small>$group</small></sup>"
}

val PATTERN_FOR_MOLECULE = sub("$1")
val PATTERN_FOR_REACTION = "$1${sub("$2")}"
val PATTERN_FOR_SHELL = "$1${sup("$2")}"
const val EMPTY_STRING = ""