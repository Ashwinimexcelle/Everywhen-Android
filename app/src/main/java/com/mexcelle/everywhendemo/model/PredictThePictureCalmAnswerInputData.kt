package com.mexcelle.everywhendemo.model

data class PredictThePictureCalmAnswerInputData(

    val setNum: String,
    val position: String,
    val coordinates: Array<Int>,
    val gyro: Array<Int>,

    )

