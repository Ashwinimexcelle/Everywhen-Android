package com.mexcelle.thoughtifydemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mexcelle.thoughtifydemo.model.*
import com.mexcelle.thoughtifydemo.retrofit.RetrofitClient
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NowAndThenRepository {

    val nowAndThenQuestionResponseData = MutableLiveData<NowAndThenResponseData?>()
    val nowAndThenAnswerResponseData = MutableLiveData<NowAndThenAnswerResponseData?>()


    fun getNowAndThenQuestion(context: Context): MutableLiveData<NowAndThenResponseData?> {

        val call = RetrofitClient.apiInterface.getNowAndThenQuestion(Constants.AUTH_TOKEN,Constants.USER_AUTHTOKEN)
        call.enqueue(object: Callback<NowAndThenResponseData> {
            override fun onFailure(call: Call<NowAndThenResponseData>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<NowAndThenResponseData>,
                response: Response<NowAndThenResponseData>
            ) {

                if(response.body()!= null){

                    if(response.body().toString() !=null){

                        val data = response.body()
                        nowAndThenQuestionResponseData.value = data!!

                    }else{

                        Utility.hideProgressDialog(context)
                        Utility.showDialog( context,"Error !!","Server Error.","Ok","Cancel")
                    }
                }else{

                    Utility.hideProgressDialog(context)
                    Utility.showDialog( context,"Error !!","Server Error.","Ok","Cancel")

                }

            }
        })

        return nowAndThenQuestionResponseData
    }





    fun postNowAndThenQuestionAnswer(context: Context, id: String,nowAndThenAnswerInputData: NowAndThenAnswerInputData): MutableLiveData<NowAndThenAnswerResponseData?> {

        val call = RetrofitClient.apiInterface.postNowAndThenQuestionAnswer(Constants.AUTH_TOKEN,Constants.USER_AUTHTOKEN,id,nowAndThenAnswerInputData)
        call.enqueue(object: Callback<NowAndThenAnswerResponseData> {
            override fun onFailure(call: Call<NowAndThenAnswerResponseData>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<NowAndThenAnswerResponseData>,
                response: Response<NowAndThenAnswerResponseData>
            ) {

                if(response.body()!= null){

                    if(response.body().toString() !=null){

                        val data = response.body()
                        nowAndThenAnswerResponseData.value = data!!

                    }else{

                        Utility.hideProgressDialog(context)
                        Utility.showDialog( context,"Error !!","Server Error.","Ok","Cancel")
                    }
                }else{

                    Utility.hideProgressDialog(context)
                    Utility.showDialog( context,"Error !!","Server Error.","Ok","Cancel")

                }

            }
        })

        return nowAndThenAnswerResponseData
    }



}