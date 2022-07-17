package com.selflearning.chemistree.games.new_approach.games.trivials

import com.selflearning.chemistree.domain.chemistry.trivials.trivials
import com.selflearning.chemistree.games.new_approach.GameRepository
import com.selflearning.chemistree.games.new_approach.GameRepository.Companion.questionsQueue
import com.selflearning.chemistree.games.new_approach.GameStages
import com.selflearning.chemistree.games.new_approach.data.GameState
import com.selflearning.chemistree.games.new_approach.games.trivials.game_type.NameByTrivial
import com.selflearning.chemistree.games.new_approach.games.trivials.game_type.TrivialByName

fun main() {
    GameTrivialsRepository()
}

class GameTrivialsRepository() : GameRepository {

    private val dataList by lazy { trivials }

    init {
        val types = listOf(
            TrivialByName,
            NameByTrivial
        )
        val games = (0..5).map {
            types.random().getGameModel()
        }

        games.forEach {
            questionsQueue.offer(it)
        }

        questionsQueue.forEach {
            println(it)
        }
    }

    override var state: GameState = GameState(lives = 4)

    /**
     *
     * */
    override fun answer(answer: String): GameStages {
        val isRightAnswer = answer == state.gameModel.gameQuestion.answer
        state = state.copy(
            gameModel = state.gameModel.copy(
                auxiliaryList = updateAnswerList(answer, isRightAnswer)
            ),
            lives = if (isRightAnswer) state.lives else --state.lives,
            wastedLives = if (isRightAnswer) state.wastedLives else ++state.wastedLives,
            score = if (isRightAnswer) state.score + 420 else state.score - 140,
//            additionalInfo = if (isRightAnswer) "" else getAdditionalInfo(answer)
        )
        questionsQueue.remove()
        return if (isRightAnswer) {
            GameStages.RightAnswer(state, listOf())
        } else {
            GameStages.WrongAnswer(state, listOf())
        }
    }

    private fun getAdditionalInfo(answer: String): String {
        val trivial = dataList.first { it.formula == answer } // TODO: 13.07.2022 вернуться может не только формула, но и название
        val trivialName = trivial.name()
        return "$answer это ${trivialName}, а ${state.gameModel.gameQuestion.question} это ${state.gameModel.gameQuestion.answer}"
    }

    /**
     *
     * */
//    private fun updateAnswerList(
//        answer: String,
//        isRightAnswer: Boolean
//    ) = state
//        .gameModel
//        .auxiliaryList
//        .map {
//            if (it.answerVariant == answer) {
//                it.copy(
//                    isCorrect = isRightAnswer,
//                    isClickable = false
//                )
//            } else it.copy(
//                isClickable = false
//            )
//        }

    private fun getRandom(range: MutableList<Int>): Int {
        val index = range.random()
        range -= index
        return index
    }
}