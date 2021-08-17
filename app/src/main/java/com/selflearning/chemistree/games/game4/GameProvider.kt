package com.selflearning.chemistree.games.game4

import android.content.Context
import com.selflearning.chemistree.chemistry.inorganic.acids.Acid
import selflearning.chemistree.domain.chemistry.elements.Element
import java.util.*

const val COUNT_OF_BUTTONS = 8

class GameProvider internal constructor(context: Context?) {
    private val listForRV: MutableList<String?> = ArrayList()
    private val isButtonsEnableList: MutableList<Boolean> = ArrayList()
    private val acidsList: List<Acid>? = null
    private val elementList: List<Element>? = null
    val question: String? = null

    fun loadData() {
        val randomAcid = (Math.random() * acidsList!!.size).toInt()
        //        String formula = acidsList.get(randomAcid).getAcidFormula();
//        List<String> stringList = FormulaTransformations.disassembleFormula(formula);
//
//        question = acidsList.get(randomAcid).getAcidRuName();
//        listForRV.addAll(stringList);
        for (i in 0 until COUNT_OF_BUTTONS) {
            isButtonsEnableList.add(true)
        }
        for (i in listForRV.size until COUNT_OF_BUTTONS) {
            val randomElement = (Math.random() * elementList!!.size).toInt()
            listForRV.add(elementList[randomElement].symbol)
        }
        listForRV.shuffle()
    }

    fun getListForRV(): List<String?> {
        return listForRV
    }

    fun getIsButtonsEnableList(): List<Boolean> {
        return isButtonsEnableList
    }
}