package com.selflearning.chemistree.games.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PreGameModel(
    val gameQuestions: List<GameQuestion>,
    val auxiliaryList: List<String>
) : Parcelable

@Parcelize
data class GameQuestion(
    val question: String,
    val answer: String
) : Parcelable