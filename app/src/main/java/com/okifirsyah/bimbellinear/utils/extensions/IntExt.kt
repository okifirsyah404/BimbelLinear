package com.okifirsyah.bimbellinear.utils.extensions

import java.text.NumberFormat
import java.util.Locale

fun Int.toRupiah(): String {
    val formatCurrency = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    val formattedCurrency = formatCurrency.format(this).substring(2)

    val result = formattedCurrency.substring(0, formattedCurrency.indexOf(','))
    if (result.contains("R")) return "Rp ${result.replace(Regex("R"), "-")}"

    return "Rp $result"
}