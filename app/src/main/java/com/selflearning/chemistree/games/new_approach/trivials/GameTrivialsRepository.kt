package com.selflearning.chemistree.games.new_approach.trivials

import com.selflearning.chemistree.domain.chemistry.trivials.Trivial
import com.selflearning.chemistree.domain.chemistry.trivials.trivials
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.new_approach.GameRepository
import com.selflearning.chemistree.games.new_approach.GameStages

class GameTrivialsRepository : GameRepository {

    private val dataList = trivials
    private val countAnswerVariants = 3

    private lateinit var trivial: Trivial
    private var gameAnswerData = mutableListOf<GameTrivialAnswerData>()

    private var intRange = (0..dataList.lastIndex).toList()


    override fun getData(): GameModel {
        val questionIndex = getRandom()
        trivial = dataList[questionIndex]
        val gameQuestion = GameQuestion(trivial.names[0].trivialNames[0].name, trivial.formula)

        return GameModel(gameQuestion, getAuxiliaryList())
    }

    private fun getAuxiliaryList(): List<GameTrivialAnswerData> {
        gameAnswerData.clear()
        var tempRange = intRange
        fun random(): Int {
            val index = tempRange.random()
            tempRange = tempRange - index
            return index
        }

        for (i in 0 until countAnswerVariants - 1) {
            gameAnswerData.add(GameTrivialAnswerData(dataList[random()].formula))
        }
        gameAnswerData.apply {
            add(GameTrivialAnswerData(trivial.formula))
            shuffle()
        }
        return gameAnswerData
    }

    override fun answer(answer: String): GameStages {
        val i = gameAnswerData.find {
            it.question == answer
        }
        return if (answer == trivial.formula) {
            GameStages.RightAnswer(Unit)
        } else {
            GameStages.WrongAnswer(trivial)
        }
    }

    private fun getRandom(): Int {
        val index = intRange.random()
        intRange = intRange - index
        return index
    }
}