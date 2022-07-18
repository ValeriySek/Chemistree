package com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type.ElementGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type.ElementGameType.Companion.temporaryListt
import com.selflearning.chemistree.games.new_approach.game_utils.questions.LanguageFactory.lang
import com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type.ElementGame
import com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type.ElementGameType
import selflearning.chemistree.domain.chemistry.elements.Element

object CountSPElectrons : ElementGame(), ElementGameType {

    override fun getGameModel(): GameModel {
        println("CountSPElectrons temporaryListSize = ${temporaryListt.size}")
        val element = temporaryListt.random()
        temporaryListt = temporaryListt - element
        println("CountSPElectrons temporaryList = $temporaryListt")
        val question = getQuestion(element)

        return GameModel(
            GameQuestion(
                question,
                element.symbol,
                question.hashCode()
            ),
            getAuxiliaryList(
                getTemporaryList().filter {
                    getElectronsCount(
                        it,
                        "s"
                    ) != getElectronsCount(element, "s")
                }
                    .map { it.symbol }.toList(),
                element.symbol,
                question.hashCode()
            )
        )
    }

    override fun hasContent() = temporaryListt.isNotEmpty()

    private fun getQuestion(element: Element): String {
        val sCount = getElectronsCount(element, "s")
        return String.format(lang().countSPElectrons(1), sCount, "s")
    }

    private fun getElectronsCount(element: Element, orbital: String) =
        element.getOrbitals(element.fullElectronConfiguration())
            .filter { it.orbital == orbital }
            .sumOf { it.electrons }
}