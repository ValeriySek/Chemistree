package com.selflearning.chemistree.games.new_approach.games.acids.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.data.GameAnswerData
import selflearning.chemistree.domain.chemistry.inorganic.acids.Acids.acids

interface AcidGameType {

    companion object {

        private val dataList by lazy { acids }

        fun getTemporaryList() = dataList
                    .shuffled().toList()
            .slice(0..5)
        var temporaryListt = getTemporaryList().toList()
    }

    fun getAuxiliaryList(
        formulaList: List<String>,
        name: String,
        questionHash: Int
    ): List<GameAnswerData> {
        return formulaList
            .shuffled()
            .minus(name)
            .slice(0..2)
            .plus(name)
            .shuffled()
            .map { GameAnswerData(answerVariant = it, questionHash = questionHash) }

    }

    fun getGameModel(): GameModel

    fun hasContent(): Boolean
}