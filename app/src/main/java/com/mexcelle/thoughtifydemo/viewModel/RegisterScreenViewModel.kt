package com.mexcelle.thoughtifydemo.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.thoughtifydemo.model.RegisterInputData
import com.mexcelle.thoughtifydemo.model.RegisterResponseData
import com.mexcelle.thoughtifydemo.repository.RegisterActivityRepository
import com.mexcelle.thoughtifydemo.util.Utility

class RegisterScreenViewModel  : ViewModel(){

    var registerResponseData: MutableLiveData<RegisterResponseData?>? = MutableLiveData<RegisterResponseData?>()
    lateinit var registerInputData: RegisterInputData

    var emailId: String = ""
    var username: String = ""
    var password: String = ""


    fun signup(context: Context): MutableLiveData<RegisterResponseData?>? {

        Log.e("emailId ","emailId "+emailId);

        if(Utility.isValidEmail(emailId)){

                registerInputData = RegisterInputData(emailId, username,password,"test1","test1")
                Log.e("DEBUG : ", registerInputData.toString())
                registerResponseData = RegisterActivityRepository.register(context, registerInputData)

        }else{

            Utility.hideProgressDialog(context)
            Utility.showDialog(context,"Register","Please enter valid EmailId.","Ok","Cancel")

        }

        return registerResponseData

    }
}