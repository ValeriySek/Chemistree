package com.selflearning.chemistree.chemistry.elements

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "elements")
class Element(
        @field:PrimaryKey val atomicNumber: Int,
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
    constructor(atomicNumber: Int,
                symbol: String,
                title: String,
                weight: Double,
                elementCategory: String)
            : this(
            atomicNumber,
            symbol,
            title,
            weight,
            -1,
            "",
            -1,
            "",
            elementCategory,
            "",
            "",
            "") {

    }
}