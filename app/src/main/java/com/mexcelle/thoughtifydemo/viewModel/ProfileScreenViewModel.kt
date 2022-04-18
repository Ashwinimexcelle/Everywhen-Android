package com.mexcelle.thoughtifydemo.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.thoughtifydemo.model.EthinicityResponseData
import com.mexcelle.thoughtifydemo.model.LoginResponseData
import com.mexcelle.thoughtifydemo.model.UpdateProfileResponseData
import com.mexcelle.thoughtifydemo.repository.ProfileFragmentRepository
import com.mexcelle.thoughtifydemo.repository.ProfileScreenFragmentRepository
import com.mexcelle.thoughtifydemo.ui.ProfileScreenFragment

class ProfileScreenViewModel :ViewModel(){

    var getProfileResponseData: MutableLiveData<LoginResponseData?> =
        MutableLiveData<LoginResponseData?>()


    fun getUserProfileDetails(context: Context): MutableLiveData<LoginResponseData?> {


        getProfileResponseData = ProfileScreenFragmentRepository.getUserDetails(context)
        //ProfileScreenFragment().isGetProfileApiCalled = true
        return getProfileResponseData


        /*if(!ProfileScreenFragment().isGetProfileApiCalled){

            getProfileResponseData = ProfileScreenFragmentRepository.getUserDetails(context)
            ProfileScreenFragment().isGetProfileApiCalled = true
            return getProfileResponseData

        }else{

            return getProfileResponseData

        }*/
    }


    fun consumeResponse(getProfileResponseDatax: LoginResponseData? ) {
        getProfileResponseData.value = getProfileResponseDatax
    }



}