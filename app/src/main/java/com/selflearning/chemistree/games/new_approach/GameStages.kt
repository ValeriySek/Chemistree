package com.selflearning.chemistree.games.new_approach

import com.selflearning.chemistree.games.new_approach.data.GameAnswerData
import com.selflearning.chemistree.games.new_approach.data.GameState

sealed class GameStages {

    data class NewQuestion(val data: GameState) : GameStages()
    data class RightAnswer(val data: GameState) : GameStages()
    data class WrongAnswer(val data: GameState) : GameStages()
    data class GameEnd(val data: GameState): GameStages()
}
