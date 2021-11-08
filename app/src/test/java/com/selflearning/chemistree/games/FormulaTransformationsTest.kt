package com.selflearning.chemistree.games

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

class FormulaTransformationsTest{

    @Test
    fun test() {
        val result = FormulaTransformations.disassembleFormula("HCl")
        assertEquals(result, listOf("H", "Cl"))
    }
}