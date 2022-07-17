package com.selflearning.chemistree.games.new_approach.data

data class ForSave(
    val type: Int,
    val question: String,
    val answer: String
)

object SavedData {

    val list = mutableListOf<ForSave>()
}