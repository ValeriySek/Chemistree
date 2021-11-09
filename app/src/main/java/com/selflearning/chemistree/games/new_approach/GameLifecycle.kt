package com.selflearning.chemistree.games.new_approach

import android.util.Log

interface GameLifecycle {

    fun onStartGame() {
        Log.i("TAGG", "GameLifecycle onStartGame()")
    }

    fun onRightAnswer()

    fun onWrongAnswer()

    fun onFinishGame()
}