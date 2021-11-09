package com.selflearning.chemistree.domain.chemistry.elements

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ElementDao {

    @Query("SELECT * FROM element")
    fun getAll(): Flow<List<Element>>

    @Query("SELECT * FROM element WHERE atomicNumber = :id")
    fun getElementById(id: Int): Element

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(elements: List<Element>)
}