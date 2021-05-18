package com.selflearning.chemistree.feature.f_games.gamespack

import com.selflearning.chemistree.games.GameComponents

class GamesRowType(
        private val gameComponents: GameComponents
        ) : RowType {
    val name: String
    val id: Int
    val background: Int

    init {
        name = gameComponents.nameOfGame
        id = gameComponents.id
        background = gameComponents.back
    }
}