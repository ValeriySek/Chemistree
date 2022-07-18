package com.selflearning.chemistree.games.new_approach.games.trivials.game_type

import com.selflearning.chemistree.domain.chemistry.trivials.trivials
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.data.GameAnswerData
import com.selflearning.chemistree.games.new_approach.game_utils.Game

interface TrivialGameType : Game {

    companion object {

        private val dataList by lazy { trivials }
//        private val dataList: List<Trivial> = trivials
        fun getTemporaryList() = dataList
//            .shuffled()
//            .slice(0..5)
        var temporaryListt = getTemporaryList().toList()
    }

    fun getGameModel(): GameModel

    fun hasContent(): Boolean

}