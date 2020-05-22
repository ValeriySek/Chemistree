package com.selflearning.chemistree.chemistry.inorganic.acids;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AcidsDao {
    @Query("SELECT * FROM acids")
    List<Acids> getAcids();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Acids> acids);
}
