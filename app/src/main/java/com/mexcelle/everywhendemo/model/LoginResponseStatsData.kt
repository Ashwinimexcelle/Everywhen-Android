package com.mexcelle.everywhendemo.model

data class LoginResponseStatsData(


    val todaysTrials: String,
    val totalPredictPictures: String,
    val correctPredictPictures: String,
    val totalStreaks: String,
    val streakProgress: String,
    val points: String,
    val unlockNowAndThen: String,
    val loginResponseStatsCurrenciesData: LoginResponseStatsCurrenciesData,
    val loginResponseStatsLeagueData: LoginResponseStatsLeagueData,
    val loginResponseStatsNowThenData: LoginResponseStatsNowThenData


)
