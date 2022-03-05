package com.selflearning.chemistree.games.new_approach

import com.selflearning.chemistree.games.models.GameModel

interface GameRepository {

    fun getData(): GameModel

    fun answer(answer: String): GameStages

    enum class Answer {
        RIGHT,
        WRONG
    }
}