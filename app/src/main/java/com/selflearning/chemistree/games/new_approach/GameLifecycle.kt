package com.selflearning.chemistree.games.new_approach

import com.selflearning.chemistree.games.new_approach.data.GameState

interface GameLifecycle {

    fun onNewQuestion(data: GameState)

    fun onRightAnswer(data: GameState)

    fun onWrongAnswer(data: GameState)

    fun onFinishGame(data: GameState)
}