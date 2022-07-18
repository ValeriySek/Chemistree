package com.selflearning.chemistree.games.new_approach.games.trivials

import com.selflearning.chemistree.domain.chemistry.trivials.trivials
import com.selflearning.chemistree.games.new_approach.GameRepository
import com.selflearning.chemistree.games.new_approach.GameRepository.Companion.questionsQueue
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

    private fun getAdditionalInfo(answer: String): String {
        val trivial = dataList.first { it.formula == answer } // TODO: 13.07.2022 вернуться может не только формула, но и название
        val trivialName = trivial.name()
        return "$answer это ${trivialName}, а ${state.gameModel.gameQuestion.question} это ${state.gameModel.gameQuestion.answer}"
    }

    private fun getRandom(range: MutableList<Int>): Int {
        val index = range.random()
        range -= index
        return index
    }
}