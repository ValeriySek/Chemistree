package com.selflearning.chemistree.domain.chemistry.elements

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "element")
data class Element(
        @PrimaryKey val atomicNumber: Int = 0,
        val symbol: String,
        val title: String,
        val weight: Double,
        val group: Int,
        val subgroup: String,
        val period: Int,
        val block: String,
        val elementCategory: String,
        val electronConfiguration: String,
        val electronsPerShell: String,
        val phase: String
) {

        override fun toString(): String {
                return "Element(atomicNumber=$atomicNumber, "
//                        "symbol='$symbol', title='$title', weight=$weight, group=$group, subgroup='$subgroup', period=$period, block='$block', elementCategory='$elementCategory', electronConfiguration='$electronConfiguration', electronsPerShell='$electronsPerShell', phase='$phase')\n"
        }
}