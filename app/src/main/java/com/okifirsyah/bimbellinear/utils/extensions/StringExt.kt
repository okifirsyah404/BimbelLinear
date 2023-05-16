package com.okifirsyah.bimbellinear.utils.extensions


fun String.enumValueToTitleCase(): String {
    return this.split("_")
        .joinToString(" ") { it.lowercase().replaceFirstChar { char -> char.uppercase() } }
}

fun String.titleCaseToEnumValue(): String {
    return this.split(" ")
        .joinToString("_") { it.uppercase() }
}