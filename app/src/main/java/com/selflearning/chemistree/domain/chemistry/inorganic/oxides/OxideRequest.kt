package com.selflearning.chemistree.domain.chemistry.inorganic.oxides

import com.google.gson.annotations.SerializedName

data class OxideRequest(
    @SerializedName("formula") val formula: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("charge") val charge: Int = 0,
    @SerializedName("classification") val classification: String = "",
    @SerializedName("difficult") val difficult: Int = 0
) {
}