package com.selflearning.chemistree.games.new_approach

import com.selflearning.chemistree.games.models.GameModel

interface GameRepository {

    fun getData(): GameStages

    fun answer(answer: String): GameStages
}