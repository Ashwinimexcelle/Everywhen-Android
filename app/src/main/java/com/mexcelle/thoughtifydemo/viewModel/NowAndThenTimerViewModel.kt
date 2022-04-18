package com.mexcelle.thoughtifydemo.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.thoughtifydemo.model.NowAndThenResponseData
import com.mexcelle.thoughtifydemo.repository.NowAndThenRepository

class NowAndThenTimerViewModel :ViewModel() {

    var nowAndThenQuestionResponseData: MutableLiveData<NowAndThenResponseData?>? = MutableLiveData<NowAndThenResponseData?>()


    fun getNowAndThenQuestion(context: Context): MutableLiveData<NowAndThenResponseData?>? {


        nowAndThenQuestionResponseData = NowAndThenRepository.getNowAndThenQuestion(context)

        return nowAndThenQuestionResponseData

    }


}