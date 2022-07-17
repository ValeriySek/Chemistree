package com.selflearning.chemistree.games.new_approach.games.acids.game_type

import android.content.Context
import android.content.res.Resources
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.games.acids.game_type.AcidGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.games.acids.game_type.AcidGameType.Companion.temporaryListt
import com.selflearning.chemistree.utils.extentions.getStringResource

class NameByAcid(val context: Context): AcidGameType {

    override fun getGameModel(): GameModel {
        val trivial = temporaryListt.random()
        temporaryListt = temporaryListt - trivial
        val question = trivial.formula
        val answer = context.getStringResource(trivial.name)
        return GameModel(
            GameQuestion(
                question = question,
                answer = answer,
                questionHash = question.hashCode()
            ),
            getAuxiliaryList(
                formulaList = getTemporaryList().map { context.getStringResource(it.name) },
                name = answer,
                questionHash = question.hashCode()
            )
        )
    }

    override fun hasContent() = temporaryListt.isNotEmpty()
}