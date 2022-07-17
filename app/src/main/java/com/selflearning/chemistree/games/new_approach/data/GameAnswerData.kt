package com.selflearning.chemistree.games.new_approach.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameAnswerData(
    val answerVariant: String = "",
    val isCorrect: Boolean? = null,
    val questionHash: Int = 0,
    val isClickable: Boolean = true
) : Parcelable {
}