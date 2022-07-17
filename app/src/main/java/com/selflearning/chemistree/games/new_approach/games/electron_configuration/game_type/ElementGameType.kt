package com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.data.GameAnswerData
import selflearning.chemistree.domain.chemistry.elements.Elements

interface ElementGameType {

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