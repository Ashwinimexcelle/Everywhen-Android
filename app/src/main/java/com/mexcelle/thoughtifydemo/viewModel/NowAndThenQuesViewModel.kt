package com.mexcelle.thoughtifydemo.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.thoughtifydemo.model.*
import com.mexcelle.thoughtifydemo.repository.NowAndThenRepository

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