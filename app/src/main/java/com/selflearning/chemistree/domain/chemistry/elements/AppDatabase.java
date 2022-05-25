package com.selflearning.chemistree.domain.chemistry.elements;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.selflearning.chemistree.domain.chemistry.inorganic.acids.Acid;
import com.selflearning.chemistree.domain.chemistry.inorganic.acids.AcidsDao;
import com.selflearning.chemistree.domain.chemistry.inorganic.bases.Bases;
import com.selflearning.chemistree.domain.chemistry.inorganic.bases.BasesDao;
import com.selflearning.chemistree.domain.chemistry.inorganic.oxides.Oxides;
import com.selflearning.chemistree.domain.chemistry.inorganic.oxides.OxidesDao;


@Database(
        entities = {
                Acid.class,
                Bases.class,
                Oxides.class,
                Element.class
        },
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ElementDao elementDao();

    public abstract BasesDao basesDao();

    public abstract AcidsDao acidsDao();

    public abstract OxidesDao oxidesDao();
}

