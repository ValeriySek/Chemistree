package com.selflearning.chemistree.games

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
                    val name = formula.substring(i, ++i)
                    strings.add(name)
                }
            }
        }
        return strings
    }

//    fun disassembleFormula(formula: String): List<String> {
//        val length = formula.length
//        val strings: MutableList<String> = ArrayList()
////        var i = 0
//        for (i in 0 until length) {
//            if (Character.isLetter(formula[i])) {
//                val iStart = i + 1
//                if (i < length && Character.isLowerCase(formula[i])) continue
//                val name = formula.substring(iStart, i)
//                strings.add(name)
//            } else {
//                val iStart = i
//                if (Character.isDigit(formula[i])) {
//                    if (i < length && Character.isDigit(formula[i])) continue
//                    val name = formula.substring(iStart, i)
//                    strings.add(name)
//                }
//                if (i < length && (formula[i] == '(' || formula[i] == ')')) {
//                    val name = formula.substring(i, i + 1)
//                    strings.add(name)
//                }
//            }
//        }
//        return strings
//    }
}