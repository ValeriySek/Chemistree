package com.selflearning.chemistree.games.new_approach.electron_configuration

import android.util.Log
import com.selflearning.chemistree.domain.chemistry.trivials.Trivial
import com.selflearning.chemistree.domain.chemistry.trivials.trivials
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.GameRepository
import com.selflearning.chemistree.games.new_approach.GameStages
import com.selflearning.chemistree.games.new_approach.electron_configuration.game_type.*
import com.selflearning.chemistree.games.new_approach.trivials.GameAnswerData
import selflearning.chemistree.domain.chemistry.elements.Element
import selflearning.chemistree.domain.chemistry.elements.Elements
import java.util.*

data class GameState(
    val score: Int = 0,
    val gameModel: GameModel = GameModel(),
    var lives: Int = 0,
    var wastedLives: Int = 0
)

enum class GameTypes {

    //    /**
//     * Определите, атомы каких двух из указанных в ряду элементов
//     * имеют на внешнем энергетическом уровне [n] электронов.
//     * */
    ELECTRONS_ON_LAST_SHELL,

    /**
     * Определите, двум атомам каких из указанных элементов до
     * завершения внешнего уровня не хватает [n] электрона.
     * */
    ELECTRONS_UNTIL_FULL,
//
//    /**
//     * Определите, атомы каких двух из указанных в ряду элементов имеют
//     * в основном состоянии [n] неспаренный электрон.
//     * [or]
//     * Определите, атомы каких из указанных элементов не имеют в основном
//     * состоянии неспаренных электронов.
//     * [or]
//     * Определите, у каких из указанных элементов число неспаренных
//     * электронов в основном состоянии превышает номер периода.
//     * */
//    FIND_UNPAIR_ELECTRONS,
//
    /**
     * Определите, атомы каких двух из указанных элементов имеют [n] валентных электронов.
     * */
    FIND_VALENCE_ELECTRONS,

//    /**
//     * Определите, в атомах каких двух из указанных элементов
//     * (в основном состоянии) общее число s-электронов превосходит общее
//     * число p-электронов.
//     * */
//    COMPARING_S_P_ELECTRONS_COUNT,

    /**
     * Определите, атомы каких из указанных элементов имеют в основном состоянии [n] s-электрона.
     * */
    COUNT_S_P_ELECTRONS,

    /**
     * Определите, атомы каких из указанных в ряду элементов в
     * основном состоянии имеют электронную формулу внешнего энергетического уровня ns1 .
     * */
    FIND_BY_ELECTRON_FORMULA,

//    /**
//     * Определите, атомы каких из указанных в ряду элементов в основном
//     * состоянии имеют одинаковое количество электронов на внешнем энергетическом уровне.
//     * */
//    THE_SAME_COUNT_OF_ELECTRONS
}

fun main() {
    val f = GameElectronConfigurationRepository()
}

class GameElectronConfigurationRepository : GameRepository {

    private val dataList by lazy { Elements.elements }

    private var state = GameState(lives = 4)

    private val countAnswerVariants = 3


    private val questionsQueue: Queue<GameModel> = LinkedList()

    init {
        val l = mutableListOf<ElementGameType>(
            FindByElectronFormula,
            CountSPElectrons,
            ComparingSPElectronsCount,
            FindValenceElectrons,
            ElectronsUntilFull,
            ElectronsOnLastShell
        )
        val games = (0..9).map {
            val game = l.random()
            val hasContent = game.hasContent()
            if (hasContent) game.getGameModel()
            else {
                l.remove(game)
                l.random().getGameModel()
            }
        }
        games.forEach {
            questionsQueue.offer(it)
        }
        questionsQueue.forEach {
            print(it)
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
}