package com.selflearning.chemistree.games.new_approach.electron_configuration.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.electron_configuration.game_type.ElementGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.electron_configuration.game_type.ElementGameType.Companion.temporaryListt
import selflearning.chemistree.domain.chemistry.elements.Element

class ElectronsOnLastShell: ElementGameType {

    override fun getGameModel(): GameModel {
        println("temporaryListSize = ${temporaryListt.size}")
        val element = temporaryListt.random()
        temporaryListt = temporaryListt - element
        println("temporaryListSize2 = ${temporaryListt.size}")
        val question =
            "Какой элемент имеет ${element.electronsOnLastShell()} электронов на внешнем энергетическом уровне "
        return GameModel(
            GameQuestion(
                question,
                element.symbol
            ),
            getAuxiliaryList(
                getTemporaryList().map { it.symbol }.toList(),
                element.symbol,
                question
            )
        )
    }
}