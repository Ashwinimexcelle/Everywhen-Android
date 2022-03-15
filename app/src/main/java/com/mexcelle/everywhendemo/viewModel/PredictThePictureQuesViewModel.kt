package com.mexcelle.everywhendemo.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.everywhendemo.model.*
import com.mexcelle.everywhendemo.repository.PredictThePictureRepository
import com.mexcelle.everywhendemo.repository.RegisterActivityRepository
import com.mexcelle.everywhendemo.util.Utility

class PredictThePictureQuesViewModel : ViewModel() {

    var predictThePictureResponseData: MutableLiveData<PredictThePictureResponseData?>? =
        MutableLiveData<PredictThePictureResponseData?>()

    var predictThePictureRecordAnswerResponseData: MutableLiveData<PredictThePictureRecordAnswerResponseData?>? =
        MutableLiveData<PredictThePictureRecordAnswerResponseData?>()

    fun getPredictThePicture(
        context: Context,
        mode: String
    ): MutableLiveData<PredictThePictureResponseData?>? {

        predictThePictureResponseData =
            PredictThePictureRepository.getPredictThePictureQuestion(context, mode)

        return predictThePictureResponseData

    }

    fun postPredictThePictureRapidAnswer(
        context: Context,
        id: String,
        predictThePictureRapidAnswerInputData: ArrayList<PredictThePictureRapidAnswerInputData?>
    ): MutableLiveData<PredictThePictureRecordAnswerResponseData?>? {

        predictThePictureRecordAnswerResponseData =
            PredictThePictureRepository.postPredictThePictureRapidAnswer(context, id,predictThePictureRapidAnswerInputData)

        return predictThePictureRecordAnswerResponseData

    }


    fun postPredictThePictureCalmAnswer(
        context: Context,
        id: String,
        predictThePictureCalmAnswerInputData: PredictThePictureCalmAnswerInputData
    ): MutableLiveData<PredictThePictureRecordAnswerResponseData?>? {

        predictThePictureRecordAnswerResponseData =
            PredictThePictureRepository.postPredictThePictureCalmAnswer(context, id,predictThePictureCalmAnswerInputData)

        return predictThePictureRecordAnswerResponseData

    }

}