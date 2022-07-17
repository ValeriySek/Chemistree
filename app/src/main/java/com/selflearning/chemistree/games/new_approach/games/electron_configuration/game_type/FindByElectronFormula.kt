package com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type.ElementGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.games.electron_configuration.game_type.ElementGameType.Companion.temporaryListt
import selflearning.chemistree.domain.chemistry.elements.Element

object FindByElectronFormula : ElementGame(), ElementGameType {

    override fun getGameModel(): GameModel {
        println("FindByElectronFormula temporaryListSize = ${temporaryListt.size}")
        val element = temporaryListt.random()
        temporaryListt = temporaryListt - element
        println("FindByElectronFormula temporaryList = $temporaryListt")
        val question = getQuestion(element)

        return GameModel(
            GameQuestion(
                question,
                element.symbol,
                question.hashCode()
            ),
            getAuxiliaryList(
                getTemporaryList().filter { it.formulaOfLastShell() != element.formulaOfLastShell() }
                    .map { it.symbol }.toList(),
                element.symbol,
                question.hashCode()
            )
        )
    }

    override fun hasContent() = temporaryListt.isNotEmpty()

    private fun getQuestion(element: Element): String {
        return "Определите, атомы каких из указанных в ряду элементов в основном состоянии имеют электронную формулу внешнего энергетического уровня ${element.formulaOfLastShell()} "
    }
}