package com.sep.byteClub.domain.entiry

data class QuestionEntity(
    val questionDescription: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)
