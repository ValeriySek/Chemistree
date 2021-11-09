package com.selflearning.chemistree.games.new_approach

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModelWithActions: ViewModel(), GameLifecycle {

    val startGameLiveData = MutableLiveData<String>()
    val rightAnswerLiveData = MutableLiveData<String>()
    val wrongAnswerLiveData = MutableLiveData<String>()
    val finishGameLiveData = MutableLiveData<String>()

    override fun onStartGame() {
        super.onStartGame()
        startGameLiveData.value = "onStartGame"
    }

    override fun onRightAnswer() {
        rightAnswerLiveData.value = "onRightAnswer"
    }

    override fun onWrongAnswer() {
        wrongAnswerLiveData.value = "onWrongAnswer"
    }

    override fun onFinishGame() {
        finishGameLiveData.value = "onFinishGame"
    }


}