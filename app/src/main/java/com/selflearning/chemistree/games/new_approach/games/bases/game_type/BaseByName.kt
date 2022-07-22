package com.selflearning.chemistree.games.new_approach.games.bases.game_type

import android.content.Context
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.games.bases.game_type.BaseGameType.Companion.getTemporaryList
import com.selflearning.chemistree.games.new_approach.games.bases.game_type.BaseGameType.Companion.temporaryListt
import com.selflearning.chemistree.utils.extentions.getStringResource

class BaseByName(val context: Context): BaseGameType {

    override fun getGameModel(): GameModel {
        val base = temporaryListt.random()
        temporaryListt = temporaryListt - base

        val question = context.getStringResource(base.name)

        return GameModel(
            GameQuestion(question, base.formula, question.hashCode()),
            getAuxiliaryList(
                getTemporaryList().map { it.formula },
                base.formula,
                question.hashCode()
            )
        )
    }

    override fun hasContent() = temporaryListt.isNotEmpty()
}