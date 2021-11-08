package com.selflearning.chemistree.games.new_approach

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

abstract class GameFragmentWithActions : Fragment(), GameLifecycle {

     val gameViewModelWithActions: GameViewModelWithActions by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameViewModelWithActions.startGameLiveData.observe(viewLifecycleOwner) {
            onStartGame()
        }
        gameViewModelWithActions.rightAnswerLiveData.observe(viewLifecycleOwner) {
            onRightAnswer()
        }
        gameViewModelWithActions.wrongAnswerLiveData.observe(viewLifecycleOwner) {
            onWrongAnswer()
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