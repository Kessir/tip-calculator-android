package com.kessir.tipcalculator.viewmodel

import android.app.Application
import android.databinding.BaseObservable
import com.kessir.tipcalculator.R
import com.kessir.tipcalculator.model.Calculator
import com.kessir.tipcalculator.model.TipCalculation

class CalculatorViewModel(
        private val app: Application,
        private val calculator: Calculator = Calculator()) : BaseObservable() {
    var inputCheckAmount = ""
    var inputTipPercentage = ""

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotalAmount = ""

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tipCalculation: TipCalculation) {
        outputCheckAmount = app.getString(R.string.dollar_amount, tipCalculation.checkAmount)
        outputTipAmount = app.getString(R.string.dollar_amount, tipCalculation.tipAmount)
        outputTotalAmount = app.getString(R.string.dollar_amount, tipCalculation.grandTotal)
    }

    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPercent = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPercent != null) {
            updateOutputs(calculator.calculateTip(checkAmount, tipPercent))
            clearInputs()
        }
    }

    fun clearInputs() {
        inputCheckAmount = "0.00"
        inputTipPercentage = "0"
        notifyChange()
    }
}