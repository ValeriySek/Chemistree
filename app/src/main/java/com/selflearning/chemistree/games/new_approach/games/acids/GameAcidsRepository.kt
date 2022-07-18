package com.selflearning.chemistree.games.new_approach.games.acids

import android.content.Context
import com.selflearning.chemistree.games.new_approach.GameRepository
import com.selflearning.chemistree.games.new_approach.GameRepository.Companion.questionsQueue
import com.selflearning.chemistree.games.new_approach.data.GameState
import com.selflearning.chemistree.games.new_approach.games.acids.game_type.AcidByName
import com.selflearning.chemistree.games.new_approach.games.acids.game_type.NameByAcid

fun main() {
//    GameAcidsRepository()
}

class GameAcidsRepository(val context: Context) : GameRepository {

    init {
        val types = listOf(
            AcidByName(context),
            NameByAcid(context)
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

}