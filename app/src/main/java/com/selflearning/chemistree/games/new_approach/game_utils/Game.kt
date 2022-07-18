package com.selflearning.chemistree.games.new_approach.game_utils

import com.selflearning.chemistree.games.new_approach.data.GameAnswerData

interface Game {

    fun getAuxiliaryList(
        formulaList: List<String>,
        name: String,
        questionHash: Int
    ): List<GameAnswerData> {
        return formulaList
            .shuffled()
            .minus(name)
            .slice(0..2)
            .plus(name)
            .shuffled()
            .map { GameAnswerData(answerVariant = it, questionHash = questionHash) }
    }


}