package com.mexcelle.thoughtifydemo.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.thoughtifydemo.model.*
import com.mexcelle.thoughtifydemo.repository.PredictThePictureRepository

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


    fun getPredictThePictureCalmMode(
        context: Context,
        mode: String
    ): MutableLiveData<PredictThePictureResponseData?>? {

        predictThePictureResponseData =
            PredictThePictureRepository.getPredictThePictureQuestionCalmMode(context, mode)

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