package com.selflearning.chemistree.games

import com.selflearning.chemistree.games.FormulaTransformations.getElementsFromFormula
import com.selflearning.chemistree.games.FormulaTransformations.isOxide
import com.selflearning.chemistree.games.FormulaTransformations.lastElementIsOxygen
import com.selflearning.chemistree.games.FormulaTransformations.numberOfElements
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

class FormulaTransformationsTest{

    @Test
    fun `disassembleFormula HCl to H, Cl`() {
        val result = FormulaTransformations.disassembleFormula("HCl")
        assertEquals(result, listOf("H", "Cl"))
    }
    @Test
    fun `disassembleFormula Al2(SO4)3 to Al, 2, (, S, O, 4, ), 3`() {
        val result = FormulaTransformations.disassembleFormula("Al2(SO4)3")
        assertEquals(result, listOf("Al", "2", "(", "S", "O", "4", ")", "3"))
    }


    @Test
    fun `numberOfElements HCl return 2`() {
        val result = FormulaTransformations.disassembleFormula("HCl").numberOfElements()
        assertEquals(result, 2)
    }

    @Test
    fun `numberOfElements H2SO4 return 3`() {
        val result = FormulaTransformations.disassembleFormula("H2SO4").numberOfElements()
        assertEquals(result, 3)
    }

    @Test
    fun `numberOfElements Al2(SO4)3 return 3`() {
        val result = FormulaTransformations.disassembleFormula("Al2(SO4)3").numberOfElements()
        assertEquals(result, 3)
    }

    @Test
    fun `lastElementIsOxygen H2O return true`() {
        val result = FormulaTransformations.disassembleFormula("H2O").lastElementIsOxygen()
        assertEquals(result, true)
    }

    @Test
    fun `lastElementIsOxygen H2O2 return true`() {
        val result = FormulaTransformations.disassembleFormula("H2O2").lastElementIsOxygen()
        assertEquals(result, true)
    }

    @Test
    fun `lastElementIsOxygen Al2(SO4)3 return true`() {
        val result = FormulaTransformations.disassembleFormula("Al2(SO4)3").lastElementIsOxygen()
        assertEquals(result, true)
    }

    @Test
    fun `lastElementIsOxygen Al(OH)3 return false`() {
        val result = FormulaTransformations.disassembleFormula("Al(OH)3").lastElementIsOxygen()
        assertEquals(result, false)
    }

    @Test
    fun `isOxide H2O return true`() {
        val result = FormulaTransformations.disassembleFormula("H2O").isOxide()
        assertEquals(result, true)
    }

    @Test
    fun `isOxide H2SO4 return false`() {
        val result = FormulaTransformations.disassembleFormula("H2SO4").isOxide()
        assertEquals(result, false)
    }

    @Test
    fun `getElementsFromFormula HCl to H, Cl`() {
        val result = FormulaTransformations.disassembleFormula("HCl").getElementsFromFormula()
        assertEquals(result, listOf("H", "Cl"))
    }

    @Test
    fun `getElementsFromFormula Al2(SO4)3 to Al, S, O`() {
        val result = FormulaTransformations.disassembleFormula("Al2(SO4)3").getElementsFromFormula()
        assertEquals(result, listOf("Al", "S", "O"))
    }
}