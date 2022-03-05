package com.selflearning.chemistree.games.new_approach

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selflearning.chemistree.games.models.GameModel

class GameViewModelWithActions(
    val gameRepository: GameRepository
): ViewModel() {

    val startGameLiveData = MutableLiveData<GameModel>()
    val rightAnswerLiveData = MutableLiveData<String>()
    val wrongAnswerLiveData = MutableLiveData<Any>()
    val finishGameLiveData = MutableLiveData<String>()

    fun getQuestion() {
        startGameLiveData.value = gameRepository.getData()
    }

    fun onRightAnswer() {
        rightAnswerLiveData.value = "onRightAnswer"
    }

    fun onWrongAnswer(data: Any) {
        wrongAnswerLiveData.value = data
    }

    fun onFinishGame() {
        finishGameLiveData.value = "onFinishGame"
    }

    fun answer(answer: String) {
        when (val answer = gameRepository.answer(answer)) {
           is GameStages.RightAnswer -> {
                onRightAnswer()
            }
            is GameStages.WrongAnswer -> {
                onWrongAnswer(answer.data)
            }
            else -> onFinishGame()
        }
    }

}