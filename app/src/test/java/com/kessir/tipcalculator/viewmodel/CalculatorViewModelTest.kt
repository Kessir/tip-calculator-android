package com.kessir.tipcalculator.viewmodel

import android.app.Application
import com.kessir.tipcalculator.R
import com.kessir.tipcalculator.model.Calculator
import com.kessir.tipcalculator.model.TipCalculation
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CalculatorViewModelTest {
    lateinit var calculatorViewModel: CalculatorViewModel
    @Mock
    lateinit var mockCalculator: Calculator
    @Mock
    lateinit var application: Application

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        calculatorViewModel = CalculatorViewModel(application,mockCalculator)
    }

    fun stubResource(given: Double, returnStub: String) {
        `when`(application.getString(R.string.dollar_amount, given)).thenReturn(returnStub)
    }

    @Test
    fun calculateTip() {
        calculatorViewModel.inputCheckAmount = "20.00"
        calculatorViewModel.inputTipPercentage = "15"

        val stub = TipCalculation(checkAmount = 20.0, tipPercentage = 15, tipAmount = 3.0, grandTotal = 23.0)
        `when`(mockCalculator.calculateTip(20.0, 15)).thenReturn(stub)
        stubResource(20.0, "$20.00")
        stubResource(3.0, "$3.00")
        stubResource(23.0, "$23.00")

        calculatorViewModel.calculateTip()

//        assertEquals(stub, calculatorViewModel.tipCalculation)
        assertEquals("$20", calculatorViewModel.outputCheckAmount)
        assertEquals("$3.00", calculatorViewModel.outputTipAmount)
        assertEquals("$23.00", calculatorViewModel.outputTotalAmount)
    }

    @Test
    fun `calculateTip bad tip percent`() {
        calculatorViewModel.inputCheckAmount = "20.00"
        calculatorViewModel.inputTipPercentage = ""

        calculatorViewModel.calculateTip()

        verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())
    }

    @Test
    fun `calculateTip bad check amount`() {
        calculatorViewModel.inputCheckAmount = ""
        calculatorViewModel.inputTipPercentage = "15"

        calculatorViewModel.calculateTip()

        verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())
    }
}