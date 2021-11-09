package com.selflearning.chemistree.games

import com.selflearning.chemistree.utils.extentions.allLetters
import java.util.ArrayList

object FormulaTransformations {

    fun disassembleFormula(formula: String): List<String> {
        val length = formula.length
        val strings: MutableList<String> = ArrayList()
        var i = 0
        while (i < length) {
            if (Character.isLetter(formula[i])) {
                val iStart = i++
                while (i < length && Character.isLowerCase(formula[i])) i++
                val name = formula.substring(iStart, i)
                strings.add(name)
            } else {
                val iStart = i
                if (Character.isDigit(formula[i])) {
                    while (i < length && Character.isDigit(formula[i])) i++
                    val name = formula.substring(iStart, i)
                    strings.add(name)
                }
                if (i < length && (formula[i] == '(' || formula[i] == ')')) {
                    val start = i++
                    val name = formula.substring(start, i)
                    strings.add(name)
                }
            }
        }
        return strings
    }

    fun List<String>.numberOfElements(): Int {
        return getElementsFromFormula().size
    }


    fun List<String>.getElementsFromFormula(): List<String> = filter { it.allLetters() }

    fun List<String>.lastElementIsOxygen(): Boolean {
        return if(!last().allLetters()) {
            slice(0 until lastIndex).lastElementIsOxygen()
        } else {
            last() == "O"
        }
    }

    fun List<String>.isOxide(): Boolean = numberOfElements() == 2 && lastElementIsOxygen()
}