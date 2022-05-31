package com.selflearning.chemistree.games.new_approach

interface GameLifecycle<T> {

    fun onNewQuestion(data: T)

    fun onRightAnswer(data: T)

    fun onWrongAnswer(data: T)

    fun onFinishGame(data: T)
}