package com.selflearning.chemistree.chemistry.elements;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import selflearning.chemistree.domain.chemistry.elements.Element;

//@Dao
//public interface ElementDao {
//
//    @Query("SELECT * FROM elements")
//    List<Element> getAllElements();
//
//    @Query("SELECT * FROM elements WHERE atomicNumber = :id")
//    Element getElementById(int id);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertAll(List<Element> elements);
//}
