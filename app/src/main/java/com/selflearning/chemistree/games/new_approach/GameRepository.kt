package com.selflearning.chemistree.games.new_approach

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.data.GameState
import java.util.*

interface GameRepository {


    companion object {

        val questionsQueue: Queue<GameModel> = LinkedList()
    }


    var state: GameState

    fun getData(): GameStages {
        val hasLives = state.lives > 0
        val hasQuestions = questionsQueue.size > 0
        return if (hasLives && hasQuestions) {
            state = state.copy(gameModel = questionsQueue.element())
            GameStages.NewQuestion(state)
        } else {
            GameStages.GameEnd(state)
        }
    }

    fun answer(answer: String): GameStages

    fun updateAnswerList(
        answer: String,
        isRightAnswer: Boolean
    ) = state
        .gameModel
        .auxiliaryList
        .map {
            if (it.answerVariant == answer) {
                it.copy(
                    isCorrect = isRightAnswer,
                    isClickable = false
                )
            } else it.copy(
                isClickable = false
            )
        }
}