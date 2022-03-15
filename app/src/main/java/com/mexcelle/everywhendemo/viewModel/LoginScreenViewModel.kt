package com.mexcelle.everywhendemo.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.everywhendemo.model.LoginInputData
import com.mexcelle.everywhendemo.model.LoginResponseData
import com.mexcelle.everywhendemo.model.RegisterResponseData
import com.mexcelle.everywhendemo.repository.LoginActivityRepository
import com.mexcelle.everywhendemo.util.Constants
import com.mexcelle.everywhendemo.util.Utility

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
            Utility.showDialog(context,"Register","Please enter valid EmailId.","Ok","Cancel")

        }

        return loginResponseData

    }

}