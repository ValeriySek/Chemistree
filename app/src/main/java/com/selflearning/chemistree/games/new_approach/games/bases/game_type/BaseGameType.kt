package com.selflearning.chemistree.games.new_approach.games.bases.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.game_utils.Game
import selflearning.chemistree.domain.chemistry.inorganic.bases.Bases
import selflearning.chemistree.domain.chemistry.inorganic.bases.Bases.bases

interface BaseGameType : Game {

    companion object {

        private val dataList by lazy { bases }

        fun getTemporaryList() = dataList
                    .shuffled().toList()
            .slice(0..5)
        var temporaryListt = getTemporaryList().toList()
    }

    fun getGameModel(): GameModel

    fun hasContent(): Boolean
}