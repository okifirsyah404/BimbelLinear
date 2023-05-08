package com.okifirsyah.bimbellinear.utils.extensions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Calendar.getGreetings(): String {
    val timeOfDay = get(Calendar.HOUR_OF_DAY)
    return when (timeOfDay) {
        in 0..11 -> "Selamat Pagi"
        in 12..15 -> "Selamat Siang"
        in 16..18 -> "Selamat Sore"
        in 19..23 -> "Selamat Malam"
        else -> "Selamat Datang"
    }
}

fun Date.getFormattedDate(): String {
    val format = "dd MMMM yyyy"
    val formatter = SimpleDateFormat(format, Locale("id", "ID"))
    return formatter.format(this)
}

fun Date.getFormattedTime(): String {
    val format = "HH:mm"
    val formatter = SimpleDateFormat(format, Locale("id", "ID"))
    return formatter.format(this)
}

fun Date.getFormattedDateTime(): String {
    val format = "dd MMMM yyyy HH:mm"
    val formatter = SimpleDateFormat(format, Locale("id", "ID"))
    return formatter.format(this)
}