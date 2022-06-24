package com.selflearning.chemistree.games.new_approach.electron_configuration.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.electron_configuration.game_type.ElementGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.electron_configuration.game_type.ElementGameType.Companion.temporaryListt
import selflearning.chemistree.domain.chemistry.elements.Element

object ElectronsUntilFull : ElementGame(), ElementGameType {

    override fun getGameModel(): GameModel {
        println("ElectronsUntilFull temporaryListSize = ${temporaryListt.size}")
        var maxElectrons = 0
        val element = temporaryListt.filter {
            maxElectrons = if (it.period == 1) 2 else 8
            it.electronsOnLastShell() < maxElectrons
        }.random()
        temporaryListt = temporaryListt - element
        println("ElectronsUntilFull temporaryList = $temporaryListt")
        val question = getQuestion(element)

        return GameModel(
            GameQuestion(
                question,
                element.symbol,
                question.hashCode()
            ),
            getAuxiliaryList(
                getTemporaryList().filter { maxElectrons - it.electronsOnLastShell() != maxElectrons - element.electronsOnLastShell() }
                    .map { it.symbol }.toList(),
                element.symbol,
                question.hashCode()
            )
        )
    }

    override fun hasContent() = temporaryListt.any {
            val maxElectrons = if (it.period == 1) 2 else 8
            it.electronsOnLastShell() < maxElectrons
        }


    private fun getQuestion(element: Element): String {
        return "Какому элементу не хватает ${8 - element.electronsOnLastShell()} электронов до заполнения внешнего уровня "
    }
}