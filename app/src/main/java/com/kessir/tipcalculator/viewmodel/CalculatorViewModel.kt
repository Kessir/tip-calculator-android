package com.kessir.tipcalculator.viewmodel

import android.app.Application
import com.kessir.tipcalculator.R
import com.kessir.tipcalculator.model.Calculator
import com.kessir.tipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(
        app: Application, private val calculator: Calculator = Calculator()) : ObservableViewModel(app) {

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotalAmount = ""

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tipCalculation: TipCalculation) {
        outputCheckAmount = getApplication<Application>().getString(R.string.dollar_amount, tipCalculation.checkAmount)
        outputTipAmount = getApplication<Application>().getString(R.string.dollar_amount, tipCalculation.tipAmount)
        outputTotalAmount = getApplication<Application>().getString(R.string.dollar_amount, tipCalculation.grandTotal)
    }

    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPercent = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPercent != null) {
            updateOutputs(calculator.calculateTip(checkAmount, tipPercent))
            clearInputs()
            notifyChange()
        }
    }

    fun clearInputs() {
        inputCheckAmount = "0.00"
        inputTipPercentage = "0"
    }
}