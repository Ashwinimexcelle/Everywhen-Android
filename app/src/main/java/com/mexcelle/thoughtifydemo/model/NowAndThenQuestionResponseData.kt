package com.mexcelle.thoughtifydemo.model

data class NowAndThenQuestionResponseData(

    val id: String,
    val title: String,
    val type: String,
    val options: ArrayList<String>,
    val publishDate : String


    )