package com.selflearning.chemistree.feature.f_games.gamespack

import com.selflearning.chemistree.games.GameComponents

class GamesRowType(
    private val gameComponents: GameComponents
) : RowType {
    val name: String = gameComponents.nameOfGame
    val id: Int = gameComponents.id
    val background: Int = gameComponents.back
}