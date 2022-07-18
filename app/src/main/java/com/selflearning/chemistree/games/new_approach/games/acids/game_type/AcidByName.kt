package com.selflearning.chemistree.games.new_approach.games.acids.game_type

import android.content.Context
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.games.acids.game_type.AcidGameType
import com.selflearning.chemistree.games.new_approach.games.acids.game_type.AcidGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.games.acids.game_type.AcidGameType.Companion.temporaryListt
import com.selflearning.chemistree.utils.extentions.getStringResource

class AcidByName(val context: Context): AcidGameType {

    override fun getGameModel(): GameModel {
        val trivial = temporaryListt.random()
        temporaryListt = temporaryListt - trivial

        val question = context.getStringResource(trivial.name)

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