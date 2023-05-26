package com.okifirsyah.bimbellinear.data.model

data class BookModel(
    val id: Int,
    val title: String,
    val grade: String,
    val image: String? = null,
)