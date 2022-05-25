package com.selflearning.chemistree.games.new_approach.trivials

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameTrivialAnswerData(
    val answerVariant: String = "",
    val isCorrect: Boolean? = null,
    val question: String = "",
    val isClickable: Boolean = true
) : Parcelable {
}