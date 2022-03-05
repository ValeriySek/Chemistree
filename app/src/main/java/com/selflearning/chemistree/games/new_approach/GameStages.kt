package com.selflearning.chemistree.games.new_approach

sealed class GameStages {

    data class RightAnswer(val data: Any) : GameStages()
    data class WrongAnswer(val data: Any) : GameStages()
    object GameEnd: GameStages()
}
