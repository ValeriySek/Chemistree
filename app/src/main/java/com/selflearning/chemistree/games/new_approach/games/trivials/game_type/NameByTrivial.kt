package com.selflearning.chemistree.games.new_approach.games.trivials.game_type

import com.selflearning.chemistree.domain.chemistry.trivials.Trivial
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.games.trivials.game_type.TrivialGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.games.trivials.game_type.TrivialGameType.Companion.temporaryListt

object NameByTrivial : TrivialGameType {

    override fun getGameModel(): GameModel {
        val trivial = temporaryListt.random()
        temporaryListt = temporaryListt - trivial
        val question = trivial.formula
        val answer = trivial.name()
        return GameModel(
            GameQuestion(
                question = question,
                answer = answer,
                questionHash = question.hashCode()
            ),
            getAuxiliaryList(
                formulaList = getTemporaryList().map { it.name() },
                name = answer,
                questionHash = question.hashCode()
            )
        )
    }

    override fun hasContent() = temporaryListt.isNotEmpty()
}