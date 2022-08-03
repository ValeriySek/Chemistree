package com.selflearning.chemistree.games.new_approach.games.characteristic_element.game_type

import android.util.Log
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.games.characteristic_element.game_type.CharacteristicGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.games.characteristic_element.game_type.CharacteristicGameType.Companion.temporaryListt
import selflearning.chemistree.domain.chemistry.elements.Element

class MetalicProperties: CharacteristicGameType {

    override fun getGameModel(): GameModel {
        val element = temporaryListt.random()
        val period = element.period
        temporaryListt = temporaryListt - element
        val elements = temporaryListt.filter {
            it.period == period
        }.shuffled().take(2).plus(element)

        print("elements $elements")

        val question = getQuestionm(element)

        return GameModel(
            GameQuestion(
                question,
                element.symbol,
                question.hashCode()
            ),
            getAuxiliaryList(
                getTemporaryList().filter { it.period != period }.map { it.symbol }.toList(),
                element.symbol,
                question.hashCode()
            )
        )
    }

    private fun getQuestionm(element: Element): String{
        return "Какому элементу не хватает  электронов до заполнения внешнего уровня "
    }

    override fun hasContent(): Boolean {
        return true
    }
}