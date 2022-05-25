package com.selflearning.chemistree.games.new_approach

import com.selflearning.chemistree.games.new_approach.trivials.GameTrivialAnswerData

sealed class GameStages {

    data class NewQuestion(val data: Any) : GameStages()
    data class RightAnswer(val data: Any, val gameAnswerData: List<GameTrivialAnswerData>) : GameStages()
    data class WrongAnswer(val data: Any, val gameAnswerData: List<GameTrivialAnswerData>) : GameStages()
    data class GameEnd(val data: Any): GameStages()
}
