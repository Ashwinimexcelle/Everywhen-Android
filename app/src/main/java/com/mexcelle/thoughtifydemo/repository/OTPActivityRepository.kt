package com.mexcelle.thoughtifydemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mexcelle.thoughtifydemo.model.OTPInputData
import com.mexcelle.thoughtifydemo.model.OTPResponseData
import com.mexcelle.thoughtifydemo.retrofit.RetrofitClient
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import org.json.JSONObject
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

                    val jsonObject = JSONObject(response.errorBody()?.string())
                    var msg: String = ""
                    if(jsonObject.has("message")){

                        msg = (jsonObject.getString("message"))
                        Utility.hideProgressDialog(context)
                        Utility.showDialog( context,"Error !!",""+msg,"Ok","Cancel")

                    }else{

                        Utility.hideProgressDialog(context)

                        Utility.showDialog( context,"Error !!","Server Error.","Ok","Cancel")

                    }


                }

            }
        })

        return otpResponseData
    }









}