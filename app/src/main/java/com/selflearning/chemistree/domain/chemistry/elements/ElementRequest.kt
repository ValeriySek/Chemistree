package com.selflearning.chemistree.domain.chemistry.elements

import com.google.gson.annotations.SerializedName
import com.selflearning.chemistree.utilities.EMPTY_STRING

class ElementRequest(
    @SerializedName("atomic_number") val atomicNumber: Int = 0,
    @SerializedName("symbol") val symbol: String = EMPTY_STRING,
    @SerializedName("title") val title: String = EMPTY_STRING,
    @SerializedName("weight") val weight: Double = 0.0,
    @SerializedName("group") val group: Int = 0,
    @SerializedName("subgroup") val subgroup: String = EMPTY_STRING,
    @SerializedName("period") val period: Int = 0,
    @SerializedName("block") val block: String = EMPTY_STRING,
    @SerializedName("category") val elementCategory: String = EMPTY_STRING,
    @SerializedName("electron_configuration") val electronConfiguration: String = EMPTY_STRING,
    @SerializedName("electrons_per_shell") val electronsPerShell: String = EMPTY_STRING,
    @SerializedName("phase") val phase: String = EMPTY_STRING
)