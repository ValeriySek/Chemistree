package com.selflearning.chemistree.games.new_approach.electron_configuration.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.trivials.GameAnswerData
import selflearning.chemistree.domain.chemistry.elements.Element
import selflearning.chemistree.domain.chemistry.elements.Elements

interface ElementGameType {



    companion object {

        private val dataList: Sequence<Element> = Elements.elements
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
        name1: String
    ): List<GameAnswerData> {
        return formulaList
            .shuffled()
            .minus(name)
            .slice(0..3)
            .plus(name)
            .shuffled()
            .map { GameAnswerData(answerVariant = it, question = name1) }

    }

    fun getGameModel(): GameModel
}