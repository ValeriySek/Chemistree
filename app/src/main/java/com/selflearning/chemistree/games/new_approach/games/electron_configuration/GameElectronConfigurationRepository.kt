package com.selflearning.chemistree.games.new_approach.games.electron_configuration

import com.selflearning.chemistree.games.new_approach.*
import com.selflearning.chemistree.games.new_approach.GameRepository.Companion.questionsQueue
import com.selflearning.chemistree.games.new_approach.data.ForSave
import com.selflearning.chemistree.games.new_approach.data.GameState
import com.selflearning.chemistree.games.new_approach.data.SavedData
import com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type.*
import com.selflearning.chemistree.games.new_approach.games.electron_configuration.GameElectronConfigurationRepository
import com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type.ComparingSPElectronsCount
import com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type.CountSPElectrons
import com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type.ElementGameType
import java.util.*

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

    override var state: GameState = GameState(lives = 4)

    init {
        val l = mutableListOf<ElementGameType>(
//            FindByElectronFormula,
            CountSPElectrons,
            ComparingSPElectronsCount,
//            FindValenceElectrons,
//            ElectronsUntilFull,
//            ElectronsOnLastShell
        )
        val games = (0..4).map {
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
}