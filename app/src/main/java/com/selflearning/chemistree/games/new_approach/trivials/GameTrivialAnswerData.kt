package com.selflearning.chemistree.games.new_approach.trivials

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameTrivialAnswerData(
    val question: String,
    val isCorrect: Boolean? = null
) : Parcelable {
}