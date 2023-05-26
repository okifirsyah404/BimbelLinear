package com.okifirsyah.bimbellinear.utils.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


fun String.toTitleCase(): String {
    val words = split(" ")
    val titleCaseWords = words.map {
        it.lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
    return titleCaseWords.joinToString(" ")
}


fun String.isEmail(): Boolean {
    val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return !matches(emailRegex)
}

fun String.enumValueToTitleCase(): String {
    return this.split("_")
        .joinToString(" ") { it.lowercase().replaceFirstChar { char -> char.uppercase() } }
}

fun String.titleCaseToEnumValue(): String {
    return this.split(" ")
        .joinToString("_") { it.uppercase() }
}

fun String.toDate(): Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("id", "ID"))
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormat.parse(this)!!
}

fun String.isStrongPassword(): Boolean {
    if (all { it.isDigit() }) return false
    if (all { it.isLetter() }) return false
    if (all { !it.isLetterOrDigit() }) return false
    return true
}
