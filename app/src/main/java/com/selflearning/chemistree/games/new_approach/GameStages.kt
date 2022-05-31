package com.selflearning.chemistree.games.new_approach

import com.selflearning.chemistree.games.new_approach.trivials.GameAnswerData

sealed class GameStages {

    data class NewQuestion(val data: Any) : GameStages()
    data class RightAnswer(val data: Any, val gameAnswerData: List<GameAnswerData>) : GameStages()
    data class WrongAnswer(val data: Any, val gameAnswerData: List<GameAnswerData>) : GameStages()
    data class GameEnd(val data: Any): GameStages()
}
