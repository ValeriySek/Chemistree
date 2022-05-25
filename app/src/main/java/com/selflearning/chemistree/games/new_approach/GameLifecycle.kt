package com.selflearning.chemistree.games.new_approach

import android.util.Log
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.trivials.GameTrivialAnswerData

interface GameLifecycle<T> {

    fun onNewQuestion(data: T)

    fun onRightAnswer(data: T)

    fun onWrongAnswer(data: T)

    fun onFinishGame(data: T)
}