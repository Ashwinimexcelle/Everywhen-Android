package com.mexcelle.thoughtifydemo.model

data class RegisterResponseData(

    val status: String,
    val username : String,
    val email : String,
    val id : String,
    val avatar : String,
    val role : String,
    val surveyCompleted : String,
    val skipSurveyCount : String,
    val quizCompleted : String,
    val skipQuizCount : String,
    val otpToken : String,
)


