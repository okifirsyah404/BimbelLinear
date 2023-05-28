package com.okifirsyah.bimbellinear.data.model

data class FaqModel(
    val title: String,
    val question: String,
    val answer: String,
    var isExpanded: Boolean = false
)
