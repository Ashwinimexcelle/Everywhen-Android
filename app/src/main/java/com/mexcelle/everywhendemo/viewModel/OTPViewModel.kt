package com.mexcelle.everywhendemo.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.everywhendemo.model.OTPInputData
import com.mexcelle.everywhendemo.model.OTPResponseData
import com.mexcelle.everywhendemo.repository.OTPActivityRepository
import com.mexcelle.everywhendemo.util.Constants

class OTPViewModel : ViewModel() {

    var otpResponseData: MutableLiveData<OTPResponseData?>? = MutableLiveData<OTPResponseData?>()
    lateinit var otpInputData: OTPInputData

    var otp: String = ""

    fun submitOtp(context: Context): MutableLiveData<OTPResponseData?>? {

        Log.e("otp ","otp "+otp);


        otpInputData = OTPInputData(Constants.USER_OTP_TOKEN,otp)
        Log.e("DEBUG : ", otpInputData.toString())
        otpResponseData = OTPActivityRepository.submitOtp(context, otpInputData)
        return otpResponseData

    }

}