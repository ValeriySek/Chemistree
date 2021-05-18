package com.selflearning.chemistree.chemistry.inorganic.acids

import com.google.gson.annotations.SerializedName

class ListAcidsRequest(
        @SerializedName("acids") val acids: List<AcidRequest> = listOf()
) {
}