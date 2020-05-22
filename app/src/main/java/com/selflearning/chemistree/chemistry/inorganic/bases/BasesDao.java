package com.selflearning.chemistree.chemistry.inorganic.bases;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BasesDao {

    @Query("SELECT * FROM bases")
    List<Bases> getAllBases();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBase(Bases base);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllBase(List<Bases> bases);

}
