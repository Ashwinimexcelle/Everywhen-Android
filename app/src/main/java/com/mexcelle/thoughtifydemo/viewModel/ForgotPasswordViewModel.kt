package com.mexcelle.thoughtifydemo.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.thoughtifydemo.model.ChangePasswordInputData
import com.mexcelle.thoughtifydemo.model.ChangePasswordResponseData
import com.mexcelle.thoughtifydemo.model.OTPInputData
import com.mexcelle.thoughtifydemo.model.OTPResponseData
import com.mexcelle.thoughtifydemo.repository.ChangePasswordRepository
import com.mexcelle.thoughtifydemo.repository.OTPActivityRepository
import com.mexcelle.thoughtifydemo.util.Constants

class ForgotPasswordViewModel  : ViewModel(){

    var changePasswordResponseData: MutableLiveData<ChangePasswordResponseData?>? = MutableLiveData<ChangePasswordResponseData?>()
    lateinit var changePasswordInputData: ChangePasswordInputData

    var oldPassword: String = ""
    var newPassword: String = ""

    fun changePassword(context: Context): MutableLiveData<ChangePasswordResponseData?>? {

        Log.e("oldPassword ","oldPassword "+oldPassword);
        Log.e("newPassword ","newPassword "+newPassword);

        changePasswordInputData = ChangePasswordInputData(oldPassword,newPassword)
        Log.e("DEBUG : ", changePasswordInputData.toString())
        changePasswordResponseData = ChangePasswordRepository.changePassword(context, changePasswordInputData)
        return changePasswordResponseData

    }



    /*var loginResponseData: MutableLiveData<LoginResponseData?>? = MutableLiveData<LoginResponseData?>()
    lateinit var loginInputData: LoginInputData

    var emailId: String = ""
    var password: String = ""*/


}