package com.mexcelle.thoughtifydemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mexcelle.thoughtifydemo.model.RegisterInputData
import com.mexcelle.thoughtifydemo.model.RegisterResponseData
import com.mexcelle.thoughtifydemo.retrofit.RetrofitClient
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import org.json.JSONObject
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
                Log.e("DEBUG : ", ""+response.body().toString())

                if(response.body()!= null){
                    Log.e("DEBUG : ", ""+response.body().toString())

                    if(response.body().toString() !=null){

                        val data = response.body()
                        registerResponseData.value = data!!

                    }else{

                        val obj = JSONObject(response.body().toString())
                        val msg: JSONObject = obj.getJSONObject("email")

                        Utility.hideProgressDialog(context)
                        Utility.showDialog( context,"Error !!",""+msg,"Ok","Cancel")
                    }
                }else{

                    val jsonObject = JSONObject(response.errorBody()?.string())

                    var msg: String = ""
                    if(jsonObject.has("username")){

                        msg = (jsonObject.getString("username"))
                    }
                    if(jsonObject.has("email")){

                        msg = (jsonObject.getString("email"))

                    }
                    if(jsonObject.has("password")){

                        msg = (jsonObject.getString("password"))

                    }
                    Log.e("","");
                    Utility.hideProgressDialog(context)
                    Utility.showDialog( context,"Error !!",""+msg,"Ok","Cancel")

                }

            }
        })

        return registerResponseData
    }





}