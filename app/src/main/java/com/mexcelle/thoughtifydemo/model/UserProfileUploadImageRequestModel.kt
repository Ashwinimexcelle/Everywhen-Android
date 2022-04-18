package com.mexcelle.thoughtifydemo.model

import okhttp3.MultipartBody


data class UserProfileUploadImageRequestModel(

    val image: MultipartBody.Part,

)
