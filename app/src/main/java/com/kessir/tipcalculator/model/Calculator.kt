package com.kessir.tipcalculator.model

import java.math.RoundingMode

class Calculator {
    fun calculateTip(checkInput: Double, tipPercent: Int): TipCalculation {
        val tipAmount = (checkInput * (tipPercent.toDouble() / 100))
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
                .toDouble()

        val grandTotal = checkInput + tipAmount
        return TipCalculation(
                tipAmount = tipAmount,
                grandTotal = grandTotal,
                checkAmount = checkInput,
                tipPercentage = tipPercent
        )
    }

}
