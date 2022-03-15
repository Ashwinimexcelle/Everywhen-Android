package com.mexcelle.everywhendemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mexcelle.everywhendemo.model.LoginInputData
import com.mexcelle.everywhendemo.model.LoginResponseData
import com.mexcelle.everywhendemo.model.OTPInputData
import com.mexcelle.everywhendemo.model.OTPResponseData
import com.mexcelle.everywhendemo.retrofit.RetrofitClient
import com.mexcelle.everywhendemo.util.Constants
import com.mexcelle.everywhendemo.util.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object OTPActivityRepository {

    val otpResponseData = MutableLiveData<OTPResponseData?>()

    fun submitOtp(context: Context, otpInputData: OTPInputData): MutableLiveData<OTPResponseData?> {

        val call = RetrofitClient.apiInterface.submitOtp(Constants.AUTH_TOKEN,otpInputData)
        call.enqueue(object: Callback<OTPResponseData> {
            override fun onFailure(call: Call<OTPResponseData>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<OTPResponseData>,
                response: Response<OTPResponseData>
            ) {

                if(response.body()!= null){

                    if(response.body().toString() !=null){

                        val data = response.body()
                        otpResponseData.value = data!!

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

        return otpResponseData
    }









}