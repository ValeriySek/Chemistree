package com.selflearning.chemistree.games.new_approach.trivials

import android.util.Log
import com.selflearning.chemistree.domain.chemistry.trivials.Trivial
import com.selflearning.chemistree.domain.chemistry.trivials.trivials
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.GameRepository
import com.selflearning.chemistree.games.new_approach.GameStages
import com.selflearning.chemistree.games.new_approach.trivials.game_type.TrivialByName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

data class GameState(
    val trivial: Trivial = Trivial(),
    val score: Int = 0,
    val gameModel: GameModel = GameModel(),
    var lives: Int = 0,
    var wastedLives: Int = 0,
    var additionalInfo: String = ""
)

fun main() {
    GameTrivialsRepository()
}

class GameTrivialsRepository() : GameRepository {

    private val dataList by lazy { trivials }

    private var state = GameState(lives = 4)

    private val countAnswerVariants = 3

    private lateinit var trivial: Trivial


    private val questionsQueue: Queue<GameModel> = LinkedList()

    init {
        val games = (0..5).map {
            TrivialByName().getGameModel()
        }

        games.forEach {
            questionsQueue.offer(it)
        }

        questionsQueue.forEach {
            println(it)
        }
    }


    override fun getData(): GameStages {
        val hasLives = state.lives > 0
        val hasQuestions = questionsQueue.size > 0
        return if (hasLives && hasQuestions) {
            state = state.copy(gameModel = questionsQueue.element())
            GameStages.NewQuestion(state)
        } else {
            GameStages.GameEnd(state)
        }
    }

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
            additionalInfo = if (isRightAnswer) "" else getAdditionalInfo(answer)
        )
        questionsQueue.remove()
        return if (isRightAnswer) {
            GameStages.RightAnswer(state, listOf())
        } else {
            GameStages.WrongAnswer(state, listOf())
        }
    }

    private fun getAdditionalInfo(answer: String): String {
        val trivial = dataList.first { it.formula == answer }
        val trivialName = trivial.name()
        return "$answer это ${trivialName}, а ${state.gameModel.gameQuestion.question} это ${state.gameModel.gameQuestion.answer}"
    }

    /**
     *
     * */
    private fun updateAnswerList(
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

    private fun getRandom(range: MutableList<Int>): Int {
        val index = range.random()
        range -= index
        return index
    }
}