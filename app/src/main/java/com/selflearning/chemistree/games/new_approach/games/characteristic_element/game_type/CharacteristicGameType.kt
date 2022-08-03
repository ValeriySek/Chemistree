package com.selflearning.chemistree.games.new_approach.games.characteristic_element.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.game_utils.Game
import selflearning.chemistree.domain.chemistry.elements.Elements.elements
import selflearning.chemistree.domain.chemistry.inorganic.acids.Acids

interface CharacteristicGameType : Game {

    companion object {

        private val dataList by lazy { elements }

        fun getTemporaryList() = dataList
            .filter {
                it.period in 2..4
            }
            .filter {
                it.group != 18 || it.groupOld != 8
            }
            .filter {
                it.block == "s" || it.block == "p"
            }
        var temporaryListt = getTemporaryList().toList()
    }

    fun getGameModel(): GameModel

    fun hasContent(): Boolean
}