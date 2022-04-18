package com.mexcelle.thoughtifydemo.model

data class NowAndThenResponseData(

    val answeredCount: String,
    val questions: ArrayList<NowAndThenQuestionResponseData>
)
