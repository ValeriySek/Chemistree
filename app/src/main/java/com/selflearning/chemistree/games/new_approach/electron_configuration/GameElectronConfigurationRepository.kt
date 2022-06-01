package com.selflearning.chemistree.games.new_approach.electron_configuration

import android.util.Log
import com.selflearning.chemistree.domain.chemistry.trivials.Trivial
import com.selflearning.chemistree.domain.chemistry.trivials.trivials
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.GameRepository
import com.selflearning.chemistree.games.new_approach.GameStages
import com.selflearning.chemistree.games.new_approach.trivials.GameAnswerData
import selflearning.chemistree.domain.chemistry.elements.Elements
import java.util.*

data class GameState(
    val score: Int = 0,
    val gameModel: GameModel = GameModel(),
    var lives: Int = 0,
    var wastedLives: Int = 0
)

enum class GameTypes {
    ELECTRONS_ON_LAST_SHELL,
    ELECTRONS_UNTIL_FULL
}

fun main() {
    val f = GameElectronConfigurationRepository()
    f.getTemporaryList().toList().forEach {
//        println(it)
    }
}

class GameElectronConfigurationRepository : GameRepository {

    private val dataList by lazy { Elements.elements }

    private var state = GameState(lives = 4)

    private val countAnswerVariants = 3

//    private val intRange = (0..dataList.lastIndex).toList()


    private val questionsQueue: Queue<GameModel> = LinkedList()

    init {
        val temporaryList = getTemporaryList().toMutableList()
        val gameTypes = (0..10).map {
            GameTypes.values().random()
        }
        val games = gameTypes.map {
            when (it) {
                GameTypes.ELECTRONS_ON_LAST_SHELL -> {

                    val element = temporaryList.random()
                    temporaryList -= element
                    val question =
                        "Какой элемент имеет ${element.electronsOnLastShell()} электронов на внешнем энергетическом уровне "
                    GameModel(
                        GameQuestion(
                            question,
                            element.symbol
                        ),
                        getAuxiliaryList(
                            getTemporaryList().map { it.symbol }.toList(),
                            element.symbol,
                            question
                        )
                    )
                }
                GameTypes.ELECTRONS_UNTIL_FULL -> {

                    val element = temporaryList.filter {
                        it.electronsOnLastShell() < 8
                    }.random()
                    temporaryList -= element

                    val question =
                        "Какому элементу не хватает ${8 - element.electronsOnLastShell()} электронов до заполнения внешнего уровня "
                    GameModel(
                        GameQuestion(
                            question,
                            element.symbol
                        ),
                        getAuxiliaryList(
                            getTemporaryList().map { it.symbol }.toList(),
                            element.symbol,
                            question
                        )
                    )
                }
            }
        }
        games.forEach {

            print(it)
        }
        temporaryList.forEach { trivial ->
//            questionsQueue.offer(
            GameModel(
                GameQuestion(),
//                    getAuxiliaryList(formulaList, )
            )
//            )
        }
//        Log.i("TAGGG", questionsQueue.toString())
    }


    fun getTemporaryList() = dataList
        .filter {
            it.period < 6
        }
        .filter {
            it.block == "s" || it.block == "p"
        }

    private fun getAuxiliaryList(
        formulaList: List<String>,
        name: String,
        name1: String
    ): List<GameAnswerData> {
        return formulaList
            .shuffled()
            .minus(name)
            .slice(0..3)
            .plus(name)
            .shuffled()
            .map { GameAnswerData(answerVariant = it, question = name1) }

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
            score = if (isRightAnswer) state.score + 420 else state.score - 140
        )
        questionsQueue.remove()
        return if (isRightAnswer) {
            GameStages.RightAnswer(state, listOf())
        } else {
            GameStages.WrongAnswer(state, listOf())
        }
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

    private fun Trivial.name() = names[0].trivialNames[0].name
}