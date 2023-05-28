package com.okifirsyah.bimbellinear.utils.extensions

import java.text.NumberFormat
import java.util.Locale

fun Int.toRupiah(): String {
    val formatCurrency = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    val formattedCurrency = formatCurrency.format(this).substring(2)

    val commaIndex = formattedCurrency.indexOf(',')
    val rIndex = formattedCurrency.indexOf('R')
    val result = when {
        commaIndex >= 0 -> formattedCurrency.substring(0, commaIndex)
        rIndex >= 0 -> formattedCurrency.substring(0, rIndex).replace("R", "-")
        else -> formattedCurrency
    }

    return "Rp $result"
}