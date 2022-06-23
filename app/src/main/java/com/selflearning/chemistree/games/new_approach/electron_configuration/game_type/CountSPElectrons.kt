package com.selflearning.chemistree.games.new_approach.electron_configuration.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.electron_configuration.game_type.ElementGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.electron_configuration.game_type.ElementGameType.Companion.temporaryListt
import selflearning.chemistree.domain.chemistry.elements.Element

class CountSPElectrons : ElementGameType {

    override fun getGameModel(): GameModel {
        println("CountSPElectrons temporaryListSize = ${temporaryListt.size}")
        val element = temporaryListt.random()
        temporaryListt = temporaryListt - element
        val question = getQuestion(element)

        return GameModel(
            GameQuestion(
                question,
                element.symbol,
                question.hashCode()
            ),
            getAuxiliaryList(
                getTemporaryList().filter { getElectronsCount(it, "s") != getElectronsCount(element, "s") }
                    .map { it.symbol }.toList(),
                element.symbol,
                question.hashCode()
            )
        )
    }

    private fun getQuestion(element: Element): String {
        val sCount = getElectronsCount(element, "s")
        return "Какой элемент имеют в основном состоянии $sCount s-электронов "
    }

    private fun getElectronsCount(element: Element, orbital: String) = element.getOrbitals(element.fullElectronConfiguration())
        .filter { it.orbital == orbital }
        .sumOf { it.electrons }
}