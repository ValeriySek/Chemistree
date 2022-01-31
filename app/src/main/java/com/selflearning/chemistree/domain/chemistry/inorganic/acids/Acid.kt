package com.selflearning.chemistree.domain.chemistry.inorganic.acids

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.selflearning.chemistree.domain.chemistry.inorganic.Inorganic
import com.selflearning.chemistree.utils.EMPTY_STRING
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "acids")
@Parcelize
data class Acid(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val formula: String = EMPTY_STRING,
    val formulaBeauty: String = EMPTY_STRING,
    val name: String = EMPTY_STRING,
    val nameSalt: String = EMPTY_STRING,
    val anion: String = EMPTY_STRING,
    val charge: Int = 0,
    val classification: String = EMPTY_STRING,
    val difficult: Int = 0
) : Inorganic, Parcelable {

    override fun toString(): String {
        return "Acid(id=$id, formula='$formula', formulaBeauty='$formulaBeauty', name='$name', nameSalt='$nameSalt', anion='$anion', charge=$charge, classification='$classification', difficult=$difficult)\n"
    }
}