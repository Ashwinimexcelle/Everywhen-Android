package com.mexcelle.everywhendemo.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.everywhendemo.model.*
import com.mexcelle.everywhendemo.repository.LoginActivityRepository
import com.mexcelle.everywhendemo.repository.NowAndThenFragmentRepository
import com.mexcelle.everywhendemo.repository.NowAndThenRepository
import com.mexcelle.everywhendemo.util.Constants
import com.mexcelle.everywhendemo.util.Utility

class NowAndThenQuesViewModel : ViewModel() {

    var nowAndThenQuestionResponseData: MutableLiveData<NowAndThenResponseData?>? = MutableLiveData<NowAndThenResponseData?>()
    var nowAndThenAnswerResponseData: MutableLiveData<NowAndThenAnswerResponseData?>? = MutableLiveData<NowAndThenAnswerResponseData?>()


    fun getNowAndThenQuestion(context: Context): MutableLiveData<NowAndThenResponseData?>? {


        nowAndThenQuestionResponseData = NowAndThenRepository.getNowAndThenQuestion(context)

        return nowAndThenQuestionResponseData

    }


    fun postNowAndThenQuestion(context: Context,id:String,nowAndThenAnswerInputData: NowAndThenAnswerInputData): MutableLiveData<NowAndThenAnswerResponseData?>? {


        nowAndThenAnswerResponseData = NowAndThenRepository.postNowAndThenQuestionAnswer(context,id, nowAndThenAnswerInputData)

        return nowAndThenAnswerResponseData

    }

}