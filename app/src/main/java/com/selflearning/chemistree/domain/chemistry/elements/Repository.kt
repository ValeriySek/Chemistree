package com.selflearning.chemistree.domain.chemistry.elements

import androidx.lifecycle.LiveData
import com.selflearning.chemistree.domain.chemistry.inorganic.acids.Acid
import com.selflearning.chemistree.domain.chemistry.inorganic.acids.AcidsDao
import com.selflearning.chemistree.domain.chemistry.inorganic.bases.Bases
import com.selflearning.chemistree.domain.chemistry.inorganic.bases.BasesDao
import com.selflearning.chemistree.domain.chemistry.inorganic.oxides.Oxides
import com.selflearning.chemistree.domain.chemistry.inorganic.oxides.OxidesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    val database: AppDatabase
) {

    fun getBaseDao(): BasesDao {
        return database.basesDao()
    }

    fun getAcidDao(): AcidsDao {
        return database.acidsDao()
    }

    fun getOxidDao(): OxidesDao {
        return database.oxidesDao()
    }

    fun getElementDao(): ElementDao {
        return database.elementDao()
    }

    fun getAllBases(): LiveData<List<Bases>> {
        return getBaseDao().getAll()
    }

    fun getAllAcids(): Flow<List<Acid>> {
        return getAcidDao().getAll()
    }

    fun getAllOxides(): LiveData<List<Oxides>> {
        return getOxidDao().getAll()
    }

    fun getAllElements(): Flow<List<Element>> {
        return getElementDao().getAll()
    }
}