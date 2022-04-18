package com.mexcelle.thoughtifydemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mexcelle.thoughtifydemo.model.LeaderBoardResponseData
import com.mexcelle.thoughtifydemo.model.LeaguesResponseData
import com.mexcelle.thoughtifydemo.model.LoginResponseData
import com.mexcelle.thoughtifydemo.retrofit.RetrofitClient
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PurchaseFlairFragmentRepository {


    var leaderBoardResponseData: MutableLiveData<LeaderBoardResponseData?> =
        MutableLiveData<LeaderBoardResponseData?>()


    var leaguesResponseData: MutableLiveData<LeaderBoardResponseData?> =
        MutableLiveData<LeaderBoardResponseData?>()


    fun getLeaderBoardDetails(
        context: Context,
    ): MutableLiveData<LeaderBoardResponseData?> {

        val call = RetrofitClient.apiInterface.getLeaderBoardDetails(
            Constants.AUTH_TOKEN,
            Constants.USER_AUTHTOKEN,

            )
        call.enqueue(object : Callback<LeaderBoardResponseData?> {
            override fun onFailure(call: Call<LeaderBoardResponseData?>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<LeaderBoardResponseData?>,
                response: Response<LeaderBoardResponseData?>
            ) {

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        leaderBoardResponseData.value = data!!

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

        return leaderBoardResponseData
    }

    fun getLeaguesDetails(
        context: Context,
        id: String,

        ): MutableLiveData<LeaderBoardResponseData?> {

        val call = RetrofitClient.apiInterface.getLeaguesDetails(
            Constants.AUTH_TOKEN,
            Constants.USER_AUTHTOKEN,
            id)
        call.enqueue(object : Callback<LeaderBoardResponseData?> {
            override fun onFailure(call: Call<LeaderBoardResponseData?>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<LeaderBoardResponseData?>,
                response: Response<LeaderBoardResponseData?>
            ) {

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        leaguesResponseData.value = data!!

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

        return leaguesResponseData
    }

}