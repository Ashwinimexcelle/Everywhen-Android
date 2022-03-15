package com.mexcelle.everywhendemo.model

data class PredictThePictureResponseData(

    val id: String,
    val session: String,
    val totalPlayers: String,
    val randomize: String,
    val pictures: Array<PredictThePictureMainOptions>


)
