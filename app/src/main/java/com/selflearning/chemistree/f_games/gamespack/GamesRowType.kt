package com.selflearning.chemistree.f_games.gamespack

import android.app.Activity
import android.view.View
import com.selflearning.chemistree.games.GameComponents
import com.selflearning.chemistree.games.PreGameActivity
import com.selflearning.chemistree.utilities.ActivityUtilities

class GamesRowType(activity: Activity, private val gameComponents: GameComponents) : RowType {
    val name: String
    private val activity: Activity
    val id: Int
    val background: Int
    val onClickListener: View.OnClickListener
        get() = View.OnClickListener {
            ActivityUtilities.invokeNewActivity(activity, PreGameActivity::class.java, id, false)
        }

    init {
        name = gameComponents.nameOfGame
        this.activity = activity
        id = gameComponents.id
        background = gameComponents.back
    }
}