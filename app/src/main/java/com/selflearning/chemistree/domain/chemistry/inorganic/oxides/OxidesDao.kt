package com.selflearning.chemistree.domain.chemistry.inorganic.oxides

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OxidesDao {

    @Query("SELECT * FROM oxides")
    fun getAll(): LiveData<List<Oxides>>

    @Insert
    fun insertAll(oxides: List<Oxides?>?)
}