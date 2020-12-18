package com.selflearning.chemistree.chemistry.inorganic

import android.content.Context
import com.selflearning.chemistree.chemistry.inorganic.acids.Acids
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases
import com.selflearning.chemistree.dBHelper.DatabaseAccess.Companion.getInstance

class ChemArray(context: Context?) {
    private val chemArray: ChemArray? = null
    val basesList: List<Bases>
    val acidsList: List<Acids>? = null

    init {
        basesList = getInstance(context!!)!!.allBases
        //            acidsList = DatabaseAccess.getInstance(context).getAllAcids();
    }
}