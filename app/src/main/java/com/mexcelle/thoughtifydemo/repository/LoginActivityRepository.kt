package com.mexcelle.thoughtifydemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mexcelle.thoughtifydemo.model.LoginInputData
import com.mexcelle.thoughtifydemo.model.LoginResponseData
import com.mexcelle.thoughtifydemo.retrofit.RetrofitClient
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LoginActivityRepository {


    val loginResponseData = MutableLiveData<LoginResponseData?>()

    fun login(
        context: Context,
        loginInputData: LoginInputData
    ): MutableLiveData<LoginResponseData?> {

        val call = RetrofitClient.apiInterface.login(Constants.AUTH_TOKEN, loginInputData)
        call.enqueue(object : Callback<LoginResponseData> {
            override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<LoginResponseData>,
                response: Response<LoginResponseData>
            ) {

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        loginResponseData.value = data!!

                    } else {

                        Utility.hideProgressDialog(context)
                        Utility.showDialog(context, "Error !!", "Server Error.", "Ok", "Cancel")
                    }
                } else {

                    val jsonObject = JSONObject(response.errorBody()?.string())
                    var msg: String = ""

                    if (jsonObject.has("message")) {

                        msg = (jsonObject.getString("message"))
                        Utility.hideProgressDialog(context)
                        Utility.showDialog(context, "Error !!", "" + msg, "Ok", "Cancel")
                    } else {

                        Utility.hideProgressDialog(context)
                        Utility.showDialog(context, "Error !!", "Server Error.", "Ok", "Cancel")
                    }


                }

            }
        })

        return loginResponseData
    }


}