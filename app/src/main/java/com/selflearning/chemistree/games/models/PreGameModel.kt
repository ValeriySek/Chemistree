package com.selflearning.chemistree.games.models

import android.os.Parcelable
import com.selflearning.chemistree.games.new_approach.data.GameAnswerData
import kotlinx.parcelize.Parcelize

@Parcelize
data class PreGameModel(
    val gameQuestions: List<GameQuestion>,
    val auxiliaryList: List<String>
) : Parcelable

@Parcelize
data class GameQuestion(
    val question: String = "",
    val answer: String = "",
    val questionHash: Int = 0
) : Parcelable

@Parcelize
data class GameModel(
    val gameQuestion: GameQuestion = GameQuestion(),
    val auxiliaryList: List<GameAnswerData> = listOf()
) : Parcelable {

    override fun toString(): String {
        return "\n$gameQuestion\n   $auxiliaryList"
    }
}