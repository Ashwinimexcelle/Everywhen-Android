package com.mexcelle.thoughtifydemo.model

data class LoginResponseData(

    val id: String,
    val token: String,
    val username: String,
    val name: String,
    val email: String,
    val avatar: String,
    val location: String,
    val age: String,
    val gender: String,

    val role: String,
    val bio: String,
    val surveyCompleted: String,
    val skipSurveyCount: String,
    val quizCompleted: String,
    val skipQuizCount: String,
    val ethnicity: AncestralHeritage,
    val ancestralHeritage: AncestralHeritage,
    val stats: LoginResponseStatsData

    )

