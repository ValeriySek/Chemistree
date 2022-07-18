package com.selflearning.chemistree.games.new_approach.games.acids.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.data.GameAnswerData
import com.selflearning.chemistree.games.new_approach.game_utils.Game
import selflearning.chemistree.domain.chemistry.inorganic.acids.Acids.acids

interface AcidGameType : Game {

    companion object {

        private val dataList by lazy { acids }

        fun getTemporaryList() = dataList
                    .shuffled().toList()
            .slice(0..5)
        var temporaryListt = getTemporaryList().toList()
    }

    fun getGameModel(): GameModel

    fun hasContent(): Boolean
}