package com.mexcelle.thoughtifydemo.model

data class LeaderBoardResponseData (

    val all: ArrayList<LeaderBoardResponseDataDetails>,
    val group: ArrayList<LeaderBoardResponseDataDetails>,
    val leagues: ArrayList<LeaguesResponseData>,

    )


