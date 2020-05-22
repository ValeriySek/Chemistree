package com.selflearning.chemistree.chemistry.inorganic.oxides;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OxidesDao {
    @Query("SELECT * FROM oxides")
    LiveData<List<Oxides>> getOxides();

    @Insert
    void insertAll(List<Oxides> oxides);
}
