package com.selflearning.chemistree.games.new_approach.games.bases

import android.content.Context
import com.selflearning.chemistree.games.new_approach.GameRepository
import com.selflearning.chemistree.games.new_approach.GameRepository.Companion.questionsQueue
import com.selflearning.chemistree.games.new_approach.data.GameState
import com.selflearning.chemistree.games.new_approach.games.bases.game_type.BaseByName
import com.selflearning.chemistree.games.new_approach.games.bases.game_type.NameByBase

fun main() {
}

class GameBasesRepository(val context: Context) : GameRepository {

    init {
        val types = listOf(
            BaseByName(context),
            NameByBase(context)
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