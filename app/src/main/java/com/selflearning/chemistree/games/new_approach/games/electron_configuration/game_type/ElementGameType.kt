package com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.data.GameAnswerData
import com.selflearning.chemistree.games.new_approach.game_utils.Game
import selflearning.chemistree.domain.chemistry.elements.Elements

interface ElementGameType : Game {

    companion object {

        private val dataList by lazy { Elements.elements }
        fun getTemporaryList() = dataList
            .filter {
                it.period < 3
            }
            .filter {
                it.block == "s" || it.block == "p"
            }
        var temporaryListt = getTemporaryList().toList()
    }

    fun getGameModel(): GameModel

    fun hasContent(): Boolean
}