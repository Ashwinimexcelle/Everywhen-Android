package com.mexcelle.everywhendemo.model

data class PredictThePictureRapidAnswerInputData(

    var setNum: String,
    var position: String?,
    var coordinates: ArrayList<Double>?,
    var gyro: ArrayList<Float>?,
    var touchPressure: Float?
)
