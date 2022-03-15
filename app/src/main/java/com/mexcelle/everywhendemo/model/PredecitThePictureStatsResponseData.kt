package com.mexcelle.everywhendemo.model

data class PredecitThePictureStatsResponseData(


    val league: String,
    val todaysTrials: String,
    val totalPredictPictures: String,
    val correctPredictPictures: String,
    val totalStreaks: String,
    val streakProgress: String,
    val points: String,
    val unlockNowAndThen: String,
    val currencies: PredecitThePictureStatsCurrenciesResponseData

)
