package com.mexcelle.thoughtifydemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mexcelle.thoughtifydemo.model.*
import com.mexcelle.thoughtifydemo.retrofit.RetrofitClient
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ProfileFragmentRepository {

    val profileResponseData = MutableLiveData<LoginResponseData?>()
    val ethinicityResponseData = MutableLiveData<ArrayList<EthinicityResponseData?>?>()
    val ancestralHeritageResponseData =
        MutableLiveData<ArrayList<AncestralHeritageResponseData?>?>()
    val fileUploadResponse = MutableLiveData<FileUploadResponse?>()


    fun updateProfileData(
        context: Context,
        updateProfileInputData: UpdateProfileInputData
    ): MutableLiveData<LoginResponseData?> {

        val call = RetrofitClient.apiInterface.updateProfileData(
            Constants.AUTH_TOKEN,
            Constants.USER_AUTHTOKEN,
            updateProfileInputData
        )
        call.enqueue(object : Callback<LoginResponseData> {
            override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<LoginResponseData>,
                responseUpdate: Response<LoginResponseData>
            ) {

                if (responseUpdate.body() != null) {

                    if (responseUpdate.body().toString() != null) {

                        val data = responseUpdate.body()
                        profileResponseData.value = data!!

                    } else {

                        Utility.hideProgressDialog(context)
                        Utility.showDialog(context, "Error !!", "Server Error.", "Ok", "Cancel")
                    }
                } else {

                    val jsonObject = JSONObject(responseUpdate.errorBody()?.string())
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

        return profileResponseData
    }


    fun getEthinicityList(
        context: Context,
    ): MutableLiveData<ArrayList<EthinicityResponseData?>?> {

        val call = RetrofitClient.apiInterface.getEthinicityList(
            Constants.AUTH_TOKEN,
        )
        call.enqueue(object : Callback<ArrayList<EthinicityResponseData?>?> {
            override fun onFailure(call: Call<ArrayList<EthinicityResponseData?>?>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<EthinicityResponseData?>?>,
                response: Response<ArrayList<EthinicityResponseData?>?>
            ) {

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        ethinicityResponseData.value = data!!


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
/*
            override fun onResponse(
                call: Call<ArrayList<EthinicityResponseData>?>,
                response: Response<ArrayList<EthinicityResponseData>?>
            ) {
                TODO("Not yet implemented")
            }*/
        })

        return ethinicityResponseData
    }


    fun getAncestralHeritage(
        context: Context,
    ): MutableLiveData<ArrayList<AncestralHeritageResponseData?>?> {

        val call = RetrofitClient.apiInterface.getAncestralHeritageList(
            Constants.AUTH_TOKEN,
        )
        call.enqueue(object : Callback<ArrayList<AncestralHeritageResponseData?>?> {
            override fun onFailure(
                call: Call<ArrayList<AncestralHeritageResponseData?>?>,
                t: Throwable
            ) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<AncestralHeritageResponseData?>?>,
                response: Response<ArrayList<AncestralHeritageResponseData?>?>
            ) {

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        ancestralHeritageResponseData.value = data!!

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

        return ancestralHeritageResponseData
    }


    fun uploadImage(
        context: Context,
        userProfileUploadImageRequestModel: UserProfileUploadImageRequestModel
    ): MutableLiveData<FileUploadResponse?> {

        val call = RetrofitClient.apiInterface.uploadImage(
            Constants.AUTH_TOKEN,
            Constants.USER_AUTHTOKEN,
            userProfileUploadImageRequestModel.image
        )
        call?.enqueue(object : Callback<FileUploadResponse> {
            override fun onFailure(call: Call<FileUploadResponse>, t: Throwable) {
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<FileUploadResponse>,
                response: Response<FileUploadResponse>
            ) {

                Log.e("DEBUG1 : ", response.body().toString())

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        fileUploadResponse.value = data!!

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

        return fileUploadResponse
    }


}