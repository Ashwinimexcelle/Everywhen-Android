package com.mexcelle.everywhendemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mexcelle.everywhendemo.model.RegisterInputData
import com.mexcelle.everywhendemo.model.RegisterResponseData
import com.mexcelle.everywhendemo.retrofit.RetrofitClient
import com.mexcelle.everywhendemo.util.Constants
import com.mexcelle.everywhendemo.util.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RegisterActivityRepository {

    val registerResponseData = MutableLiveData<RegisterResponseData?>()

    fun register(context: Context, registerInputData: RegisterInputData): MutableLiveData<RegisterResponseData?> {

        val call = RetrofitClient.apiInterface.register(Constants.AUTH_TOKEN,registerInputData)
        call.enqueue(object: Callback<RegisterResponseData> {
            override fun onFailure(call: Call<RegisterResponseData>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<RegisterResponseData>,
                response: Response<RegisterResponseData>
            ) {

                if(response.body()!= null){

                    if(response.body().toString() !=null){

                        val data = response.body()
                        registerResponseData.value = data!!

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

        return registerResponseData
    }





}