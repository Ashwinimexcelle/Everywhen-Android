package com.mexcelle.everywhendemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mexcelle.everywhendemo.model.*
import com.mexcelle.everywhendemo.retrofit.RetrofitClient
import com.mexcelle.everywhendemo.util.Constants
import com.mexcelle.everywhendemo.util.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PredictThePictureRepository {


    val predictThePictureResponseData = MutableLiveData<PredictThePictureResponseData?>()
    var call: Call<PredictThePictureResponseData>? = null

    val predictThePictureRecordAnswerResponseData =
        MutableLiveData<PredictThePictureRecordAnswerResponseData?>()



    fun getPredictThePictureQuestion(
        context: Context,
        mode: String
    ): MutableLiveData<PredictThePictureResponseData?> {
        if (mode == "rapid") {

            call = RetrofitClient.apiInterface.getPredictThePictureQuestionRapid(
                Constants.AUTH_TOKEN,
                Constants.USER_AUTHTOKEN,
                mode
            )

        } else {

            call = RetrofitClient.apiInterface.getPredictThePictureQuestionCalm(
                Constants.AUTH_TOKEN,
                Constants.USER_AUTHTOKEN,/*mode*/
            )

        }
        call!!.enqueue(object : Callback<PredictThePictureResponseData> {
            override fun onFailure(call: Call<PredictThePictureResponseData>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<PredictThePictureResponseData>,
                response: Response<PredictThePictureResponseData>
            ) {

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        predictThePictureResponseData.value = data!!

                    } else {

                        Utility.hideProgressDialog(context)
                        Utility.showDialog(context, "Error !!", "Server Error.", "Ok", "Cancel")
                    }
                } else {

                    Utility.hideProgressDialog(context)
                    Utility.showDialog(context, "Error !!", "Server Error.", "Ok", "Cancel")

                }

            }
        })

        return predictThePictureResponseData
    }


    fun postPredictThePictureRapidAnswer(context: Context,
                                         id: String,
                                         predictThePictureRapidAnswerInputData: ArrayList<PredictThePictureRapidAnswerInputData?>
    ):
            MutableLiveData<PredictThePictureRecordAnswerResponseData?> {
        val call = RetrofitClient.apiInterface.postPredictThePictureAnswerRapid(
            Constants.AUTH_TOKEN,
            Constants.USER_AUTHTOKEN,id,
            predictThePictureRapidAnswerInputData
        )


        call.enqueue(object : Callback<PredictThePictureRecordAnswerResponseData> {
            override fun onFailure(call: Call<PredictThePictureRecordAnswerResponseData>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<PredictThePictureRecordAnswerResponseData>,
                response: Response<PredictThePictureRecordAnswerResponseData>
            ) {

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        predictThePictureRecordAnswerResponseData.value = data!!

                    } else {

                        Utility.hideProgressDialog(context)
                        Utility.showDialog(context, "Error !!", "Server Error.", "Ok", "Cancel")
                    }
                } else {

                    Utility.hideProgressDialog(context)
                    Utility.showDialog(context, "Error !!", "Server Error.", "Ok", "Cancel")

                }

            }
        })

        return predictThePictureRecordAnswerResponseData
    }



    fun postPredictThePictureCalmAnswer(context: Context,
                                         id: String,
                                         predictThePictureCalmAnswerInputData: PredictThePictureCalmAnswerInputData
    ):
            MutableLiveData<PredictThePictureRecordAnswerResponseData?> {
        val call = RetrofitClient.apiInterface.postPredictThePictureAnswerCalm(
            Constants.AUTH_TOKEN,
            Constants.USER_AUTHTOKEN,id,
            predictThePictureCalmAnswerInputData
        )


        call.enqueue(object : Callback<PredictThePictureRecordAnswerResponseData> {
            override fun onFailure(call: Call<PredictThePictureRecordAnswerResponseData>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<PredictThePictureRecordAnswerResponseData>,
                response: Response<PredictThePictureRecordAnswerResponseData>
            ) {

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        predictThePictureRecordAnswerResponseData.value = data!!

                    } else {

                        Utility.hideProgressDialog(context)
                        Utility.showDialog(context, "Error !!", "Server Error.", "Ok", "Cancel")
                    }
                } else {

                    Utility.hideProgressDialog(context)
                    Utility.showDialog(context, "Error !!", "Server Error.", "Ok", "Cancel")

                }

            }
        })

        return predictThePictureRecordAnswerResponseData
    }


}