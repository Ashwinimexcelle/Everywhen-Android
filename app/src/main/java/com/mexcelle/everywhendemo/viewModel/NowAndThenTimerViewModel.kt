package com.mexcelle.everywhendemo.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.everywhendemo.model.NowAndThenAnswerResponseData
import com.mexcelle.everywhendemo.model.NowAndThenResponseData
import com.mexcelle.everywhendemo.repository.NowAndThenRepository

class NowAndThenTimerViewModel :ViewModel() {

    var nowAndThenQuestionResponseData: MutableLiveData<NowAndThenResponseData?>? = MutableLiveData<NowAndThenResponseData?>()


    fun getNowAndThenQuestion(context: Context): MutableLiveData<NowAndThenResponseData?>? {


        nowAndThenQuestionResponseData = NowAndThenRepository.getNowAndThenQuestion(context)

        return nowAndThenQuestionResponseData

    }


}