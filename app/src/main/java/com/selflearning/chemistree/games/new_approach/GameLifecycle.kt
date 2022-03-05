package com.selflearning.chemistree.games.new_approach

import android.util.Log
import com.selflearning.chemistree.games.models.GameModel

interface GameLifecycle {

    fun onStartGame(data: GameModel) {
        Log.i("TAGG", "GameLifecycle onStartGame()")
    }

    fun onRightAnswer()

    fun onWrongAnswer(data: Any)

    fun onFinishGame()
}