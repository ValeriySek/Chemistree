package com.selflearning.chemistree.games.new_approach

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModelWithActions(
    val gameRepository: GameRepository
): ViewModel() {

    val gameState = MutableLiveData<GameStages>()

    fun getQuestion() {
        gameState.value = gameRepository.getData()
    }

    fun answer(answer: String) {
        gameState.value = gameRepository.answer(answer)
    }

}