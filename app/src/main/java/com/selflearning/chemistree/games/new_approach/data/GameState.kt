package com.selflearning.chemistree.games.new_approach.data

import com.selflearning.chemistree.domain.chemistry.trivials.Trivial
import com.selflearning.chemistree.games.models.GameModel

data class GameState(
    val trivial: Trivial = Trivial(),
    val score: Int = 0,
    val gameModel: GameModel = GameModel(),
    var lives: Int = 0,
    var wastedLives: Int = 0,
    var additionalInfo: String = ""
)
