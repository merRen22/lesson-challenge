package com.challenge.get.model

data class Lesson(
    val id: Int,
    val content: List<Step>,
    val input: Input? = null
)