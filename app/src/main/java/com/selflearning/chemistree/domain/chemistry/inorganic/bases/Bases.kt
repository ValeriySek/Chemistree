package com.selflearning.chemistree.domain.chemistry.inorganic.bases

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.selflearning.chemistree.domain.chemistry.inorganic.Inorganic

@Entity(tableName = "bases")
class Bases(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val formula: String,
    val beautifulFormula: String,
    val name: String,
    val nameCation: String,
    val cation: String,
    val oxidationState: Int,
    val classification: String,
    val difficult: Int
) : Inorganic {

    override fun toString(): String {
        return "Bases(id=$id, formula='$formula', beautifulFormula='$beautifulFormula', name='$name', nameCation='$nameCation', cation='$cation', oxidationState=$oxidationState, classification='$classification', difficult=$difficult)\n"
    }
}