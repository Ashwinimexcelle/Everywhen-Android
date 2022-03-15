package com.mexcelle.everywhendemo.model

data class LoginResponseData(

    val id: String,
    val token: String,
    val username: String,
    val email: String,
    val role: String,
    val surveyCompleted: String,
    val skipSurveyCount: String,
    val quizCompleted: String,
    val skipQuizCount: String,
    val stats: LoginResponseStatsData

    )

