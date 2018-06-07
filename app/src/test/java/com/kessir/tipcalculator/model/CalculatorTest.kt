package com.kessir.tipcalculator.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorTest {
    lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = Calculator()
    }

    @Test
    fun testCalculateTip() {
        val baseCalculation = TipCalculation(checkAmount = 10.00)

        val testValues = listOf(
                baseCalculation.copy(tipPercentage = 25, grandTotal = 12.5, tipAmount = 2.5),
                baseCalculation.copy(tipPercentage = 20, grandTotal = 12.0, tipAmount = 2.0),
                baseCalculation.copy(tipPercentage = 15, grandTotal = 11.5, tipAmount = 1.5),
                baseCalculation.copy(tipPercentage = 18, grandTotal = 11.8, tipAmount = 1.8)
        )

        testValues.forEach {
            assertEquals(
                    it,
                    calculator.calculateTip(it.checkAmount, it.tipPercentage)
            )
        }

    }
}