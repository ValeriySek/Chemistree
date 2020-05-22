package com.selflearning.chemistree.chemistry.elements;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ElementDao {

    @Query("SELECT * FROM elements")
    List<Element> getAllElements();

    @Query("SELECT * FROM elements WHERE atomicNumber = :id")
    Element getElementById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Element> elements);
}
