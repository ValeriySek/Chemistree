package com.selflearning.chemistree.games.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "game")
data class GameForDb(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val scores: List<Int> = listOf(),
)
