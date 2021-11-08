package com.selflearning.chemistree.domain.chemistry.inorganic.oxides

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.selflearning.chemistree.domain.chemistry.inorganic.Inorganic

@Entity(tableName = "oxides")
data class Oxides(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val formula: String,
    val formulaBeauty: String,
    val name: String,
    val charge: Int,
    val classification: String,
    val difficult: Int
) : Inorganic {

    override fun toString(): String {
        return "Oxides(id=$id, formula='$formula', formulaBeauty='$formulaBeauty', name='$name', charge=$charge, classification='$classification', difficult=$difficult)\n"
    }
}