package com.selflearning.chemistree.games.new_approach.games.trivials.game_type

import com.selflearning.chemistree.domain.chemistry.trivials.Trivial
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.games.trivials.game_type.TrivialGameType
import com.selflearning.chemistree.games.new_approach.games.trivials.game_type.TrivialGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.games.trivials.game_type.TrivialGameType.Companion.temporaryListt

object TrivialByName: TrivialGameType {

    override fun getGameModel(): GameModel {
        val trivial = temporaryListt.random()
        temporaryListt = temporaryListt - trivial
        val question = trivial.name()
        return GameModel(
            GameQuestion(question, trivial.formula, question.hashCode()),
            getAuxiliaryList(
                getTemporaryList().map { it.formula },
                trivial.formula,
                question.hashCode()
            )
        )
    }

    override fun hasContent() = temporaryListt.isNotEmpty()
}