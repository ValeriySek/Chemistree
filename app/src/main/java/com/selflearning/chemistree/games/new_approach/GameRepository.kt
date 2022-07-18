package com.selflearning.chemistree.games.new_approach

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.data.ForSave
import com.selflearning.chemistree.games.new_approach.data.GameState
import com.selflearning.chemistree.games.new_approach.data.SavedData
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

    fun answer(answer: String): GameStages {
        val isRightAnswer = answer == state.gameModel.gameQuestion.answer
        val gameModel = state.gameModel.copy(
            auxiliaryList = updateAnswerList(answer, isRightAnswer)
        )
        val answerHistory = state.answerHistory.apply {
            add(gameModel.copy(
                auxiliaryList = gameModel.auxiliaryList.filter {
                    it.isCorrect != null
                }
            ))
        }

        state = state.copy(
            gameModel = gameModel,
            lives = if (isRightAnswer) state.lives else --state.lives,
            wastedLives = if (isRightAnswer) state.wastedLives else ++state.wastedLives,
            score = if (isRightAnswer) state.score + 420 else state.score - 140,
            answerHistory = answerHistory
//            additionalInfo = if (isRightAnswer) "" else getAdditionalInfo(answer)
        )
        questionsQueue.remove()
        return if (isRightAnswer) {
            GameStages.RightAnswer(state)
        } else {
            GameStages.WrongAnswer(state)
        }
    }

    private fun addElementAgainToEnd() {
        SavedData.list.add(
            ForSave(
                0,
                state.gameModel.gameQuestion.question,
                state.gameModel.gameQuestion.answer
            )
        )

        questionsQueue.offer(
            state.gameModel.copy(
                auxiliaryList = state.gameModel.auxiliaryList.filter {
                    it.isCorrect == null
                }.map {
                    it.copy(
                        isClickable = true
                    )
                }
            )
        )
    }

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