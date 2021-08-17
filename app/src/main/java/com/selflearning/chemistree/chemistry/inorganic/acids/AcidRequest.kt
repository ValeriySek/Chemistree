package com.selflearning.chemistree.chemistry.inorganic.acids

import android.content.Context
import com.google.gson.annotations.SerializedName
import com.selflearning.chemistree.utilities.Transformable
import com.selflearning.chemistree.utilities.extentions.replaceOnSubstringString

data class AcidRequest(
        @SerializedName("formula") val formula: String = "",
        @SerializedName("name") val name: String = "",
        @SerializedName("salt_name") val nameSalt: String = "",
        @SerializedName("anion") val anion: String = "",
        @SerializedName("charge") val charge: Int = 0,
        @SerializedName("classification") val classification: String = "",
        @SerializedName("difficult") val difficult: Int = 0
)  {

//        override fun transform(): Acid {
//                return Acid(
//                        formula = formula,
//                        formulaBeauty = formula.replaceOnSubstringString()
//                )
//        }

}