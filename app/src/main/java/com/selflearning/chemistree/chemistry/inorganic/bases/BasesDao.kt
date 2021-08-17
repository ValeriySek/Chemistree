package com.selflearning.chemistree.chemistry.inorganic.bases

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BasesDao {

    @Query("SELECT * FROM bases")
    fun getAll(): LiveData<List<Bases>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBase(base: Bases)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBase(bases: List<Bases>)
}