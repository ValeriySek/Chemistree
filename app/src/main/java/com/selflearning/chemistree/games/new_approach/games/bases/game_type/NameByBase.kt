package com.selflearning.chemistree.games.new_approach.games.bases.game_type

import android.content.Context
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.games.bases.game_type.BaseGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.games.bases.game_type.BaseGameType.Companion.temporaryListt
import com.selflearning.chemistree.utils.extentions.getStringResource

class NameByBase(val context: Context): BaseGameType {

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