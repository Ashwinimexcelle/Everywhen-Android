package com.mexcelle.thoughtifydemo.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.thoughtifydemo.model.LoginInputData
import com.mexcelle.thoughtifydemo.model.LoginResponseData
import com.mexcelle.thoughtifydemo.repository.LoginActivityRepository
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility

class LoginScreenViewModel : ViewModel(){

    var loginResponseData: MutableLiveData<LoginResponseData?>? = MutableLiveData<LoginResponseData?>()
    lateinit var loginInputData: LoginInputData

    var emailId: String = ""
    var password: String = ""

    fun login(context: Context): MutableLiveData<LoginResponseData?>? {

        if(Utility.isValidEmail(emailId)){

            loginInputData = LoginInputData(emailId,password,Constants.USER_NOTIFICATION_TOKEN)
            Log.e("DEBUG : ", loginInputData.toString())
            loginResponseData = LoginActivityRepository.login(context, loginInputData)

        }else{

            Utility.hideProgressDialog(context)
            Utility.showDialog(context,"Login","Please enter valid EmailId.","Ok","Cancel")

        }

        return loginResponseData

    }

}