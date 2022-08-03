package com.selflearning.chemistree.games.new_approach.games.characteristic_element

import com.selflearning.chemistree.games.new_approach.GameRepository
import com.selflearning.chemistree.games.new_approach.GameStages
import com.selflearning.chemistree.games.new_approach.data.GameState
import com.selflearning.chemistree.games.new_approach.games.bases.game_type.BaseByName
import com.selflearning.chemistree.games.new_approach.games.bases.game_type.NameByBase
import com.selflearning.chemistree.games.new_approach.games.characteristic_element.game_type.MetalicProperties
import com.selflearning.chemistree.games.new_approach.games.electron_configuration.GameElectronConfigurationRepository

fun main() {
    val f = GameCharacteristicRepository()
}

class GameCharacteristicRepository : GameRepository {

    init {
        val types = listOf(
            MetalicProperties()
        )
        val games = (0..5).map {
            types.random().getGameModel()
        }

        games.forEach {
            GameRepository.questionsQueue.offer(it)
        }

        GameRepository.questionsQueue.forEach {
            println(it)
        }
    }

    override var state: GameState = GameState(lives = 4)
}