package com.selflearning.chemistree.games.new_approach

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

abstract class GameFragmentWithActions : Fragment(), GameLifecycle {

    abstract val gameRepository: GameRepository

    val gameViewModelWithActions: GameViewModelWithActions by viewModels {
        GameViewModelWithActionsFactory(gameRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameViewModelWithActions.getQuestion()
        gameViewModelWithActions.startGameLiveData.observe(viewLifecycleOwner) {
            onStartGame(it)
        }
        gameViewModelWithActions.rightAnswerLiveData.observe(viewLifecycleOwner) {
            onRightAnswer()
        }
        gameViewModelWithActions.wrongAnswerLiveData.observe(viewLifecycleOwner) {
            onWrongAnswer(it)
        }
        gameViewModelWithActions.finishGameLiveData.observe(viewLifecycleOwner) {
            onFinishGame()
        }
    }

//    abstract fun onStartGame()
//
//    abstract fun onRightAnswer()
//
//    abstract fun onWrongAnswer()
//
//    abstract fun onFinishGame()
}