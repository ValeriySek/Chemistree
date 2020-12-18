package com.selflearning.chemistree.data

data class WinData(
        val timeInMillis: Long,
        val score: Long) {
    constructor() : this(-1, -1) {

    }
}