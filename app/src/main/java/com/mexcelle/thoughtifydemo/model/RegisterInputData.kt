package com.mexcelle.thoughtifydemo.model

data class RegisterInputData(

    val email: String,
    val username: String,
    val password: String,
    val deviceId: String,
    val notificatioToken: String

)
