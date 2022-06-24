package com.selflearning.chemistree.games.new_approach.electron_configuration.game_type

import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.electron_configuration.game_type.ElementGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.electron_configuration.game_type.ElementGameType.Companion.temporaryListt
import selflearning.chemistree.domain.chemistry.elements.Element

object ComparingSPElectronsCount : ElementGame(), ElementGameType {

    override fun getGameModel(): GameModel {
        println("ComparingSPElectronsCount temporaryListSize = ${temporaryListt.size}")
        val element = temporaryListt.filter {
            getComparing(it)
        }.random()
        temporaryListt = temporaryListt - element
        println("ComparingSPElectronsCount temporaryList = $temporaryListt")
        val question = getQuestion(element)


        return GameModel(
            GameQuestion(
                question,
                element.symbol,
                question.hashCode()
            ),
            getAuxiliaryList(
                getTemporaryList().filter { !getComparing(it) }
                    .map { it.symbol }.toList(),
                element.symbol,
                question.hashCode()
            )
        )
    }

    override fun hasContent() = temporaryListt.any { getComparing(it) }

    private fun getComparing(element: Element): Boolean {
//        println("ComparingSPElectronsCount getComparing $element")
        val orbitals = element.getOrbitals(element.fullElectronConfiguration())
//        println("ComparingSPElectronsCount orbitals ")
        val sCount =
            orbitals.filter { it.orbital == "s" }.sumOf { it.electrons }
//        println("ComparingSPElectronsCount sCount $sCount ")
        val pCount =
            orbitals.filter { it.orbital == "p" }.sumOf { it.electrons }
//        println("ComparingSPElectronsCount pCount $pCount")
        return sCount < pCount
    }

    private fun getQuestion(element: Element): String {
        return "Определите, в каком элементе (в основном состоянии) общее число s-электронов превосходит общее число p-электронов "
    }
}