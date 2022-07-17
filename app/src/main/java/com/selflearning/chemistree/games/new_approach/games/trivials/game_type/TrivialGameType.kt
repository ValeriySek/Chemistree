package com.selflearning.chemistree.games.new_approach.games.trivials.game_type

import com.selflearning.chemistree.domain.chemistry.trivials.trivials
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.data.GameAnswerData

interface TrivialGameType {

    companion object {

        private val dataList by lazy { trivials }
//        private val dataList: List<Trivial> = trivials
        fun getTemporaryList() = dataList
//            .shuffled()
//            .slice(0..5)
        var temporaryListt = getTemporaryList().toList()
    }

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

    fun getGameModel(): GameModel

    fun hasContent(): Boolean
}