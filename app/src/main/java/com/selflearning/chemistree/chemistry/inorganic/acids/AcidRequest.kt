package com.selflearning.chemistree.chemistry.inorganic.acids

import com.google.gson.annotations.SerializedName

data class AcidRequest(
        @SerializedName("formula") val formula: String = "HCl",
        @SerializedName("name") val name: String = "hydrochloric",
        @SerializedName("nameSalt") val nameSalt: String = "chloride",
        @SerializedName("anion") val anion: String = "Cl",
        @SerializedName("charge") val charge: Int = -1,
        @SerializedName("classification") val classification: String = "",
        @SerializedName("difficult") val difficult: Int = 1
)