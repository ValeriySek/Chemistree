package com.selflearning.chemistree.chemistry.inorganic.acids

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AcidsDao {
    @Query("SELECT * FROM acids")
    fun getAll(): Flow<List<Acid>>

    @Query("SELECT * FROM acids WHERE difficult < :lvl")
    fun getAcidsByLevel(lvl: Int): List<Acid>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(acids: List<Acid>)
}