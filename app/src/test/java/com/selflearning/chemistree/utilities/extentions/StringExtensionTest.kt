package com.selflearning.chemistree.utilities.extentions

import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `notDigit pass digit returns false`() {
        val result = "4".notDigit()
        assertEquals(result, false)
    }

    @Test
    fun `notDigit pass string returns true`() {
        val result = "Na".notDigit()
        assertEquals(result, true)
    }

    @Test
    fun `isElement pass string returns true`() {
        val result = "Na".allLetters()
        assertEquals(result, true)
    }

    @Test
    fun `allLetters pass ( returns false`() {
        val result = "(".allLetters()
        assertEquals(result, false)
    }

    @Test
    fun `allLetters pass digit returns false`() {
        val result = "4".allLetters()
        assertEquals(result, false)
    }
}