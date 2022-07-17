package com.selflearning.chemistree.games.new_approach

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.selflearning.chemistree.games.new_approach.game_utils.Vibratorable

abstract class GameFragmentWithActions : Fragment(), GameLifecycle, Vibratorable {

    abstract val gameRepository: GameRepository

    val gameViewModelWithActions: GameViewModelWithActions by viewModels {
        GameViewModelWithActionsFactory(gameRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameViewModelWithActions.getQuestion()
        gameViewModelWithActions.gameState.observe(viewLifecycleOwner) {
            Log.i("TAHH", "gameState $it")
            when(it) {
                is GameStages.NewQuestion -> {
                    onNewQuestion(it.data)
                }
                is GameStages.RightAnswer -> {
                    onRightAnswer(it.data)
                }
                is GameStages.WrongAnswer -> {
                    vibrate()
                    onWrongAnswer(it.data)
                }
                is GameStages.GameEnd -> {
                    onFinishGame(it.data)
                }
            }
        }
    }
}