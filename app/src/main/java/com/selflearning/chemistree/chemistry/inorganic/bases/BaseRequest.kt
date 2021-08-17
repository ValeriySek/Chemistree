package com.selflearning.chemistree.chemistry.inorganic.bases

import com.google.gson.annotations.SerializedName

data class BaseRequest(
    @SerializedName("formula") val formula: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("oxidation_state") val oxidationState: Int = 0,
    @SerializedName("cation") val cation: String = "N",
    @SerializedName("classification") val classification: String = "",
    @SerializedName("difficult") val difficult: Int = 0
)