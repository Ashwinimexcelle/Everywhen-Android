package com.mexcelle.thoughtifydemo.ui

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.PredictThePictureQuesFragmentBinding
import com.mexcelle.thoughtifydemo.model.PredictThePictureOptions
import com.mexcelle.thoughtifydemo.model.PredictThePictureRapidAnswerInputData
import com.mexcelle.thoughtifydemo.model.PredictThePictureResponseData
import com.mexcelle.thoughtifydemo.ui.adapter.RecyclerViewAdapter
import com.mexcelle.thoughtifydemo.util.Utility
import com.mexcelle.thoughtifydemo.viewModel.PredictThePictureQuesViewModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_predict_the_picture_ques.*
import java.util.*


class PredictThePictureQuestionFragment : Fragment() {

    private lateinit var predictThePictureQuesViewModel: PredictThePictureQuesViewModel
    private var _binding: PredictThePictureQuesFragmentBinding? = null
    lateinit var mActivity: Activity
    public lateinit var navController: NavController
    var mProgressBar: ProgressBar? = null
    var mCountDownTimer: CountDownTimer? = null
    var isTimeUp: Boolean = false
    var mode: String = ""
    var idQues: String = ""
    var totalNumberOfQuestions: Int = 0
    var totalNumberOfOptionsInOneQuestion: Int = 0
    var questionCounter: Int = 0
    var answerArray: ArrayList<String>? = null
    var selectedAnswer: String? = null
    var answerObject: ArrayList<PredictThePictureRapidAnswerInputData?> =
        ArrayList<PredictThePictureRapidAnswerInputData?>()
    var questionCount: Int = 1
    var isGetPredictPictureShown: Boolean = false
    private lateinit var timer: CountDownTimer
    private lateinit var timerCalm: CountDownTimer


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        predictThePictureQuesViewModel = ViewModelProvider(this).get(
            PredictThePictureQuesViewModel::class.java
        )
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_predict_the_picture_ques, container, false
        )
        binding.setLifecycleOwner(this)
        binding.predictThePictureQuesViewModel = predictThePictureQuesViewModel
        return binding.getRoot()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (isAdded && requireActivity() != null) {

            if (activity != null) {
                mActivity = requireActivity()
                Log.e("", "here in is added");
                init()

            }

        } else {

            Log.e("", "here in is not added");

        }
        Log.e("in INIT", "in INIT activity created");


    }

    private fun init() {

        navController = findNavController()

        Utility.setRegular(mActivity, txt1)
        Utility.setRegular(mActivity, txt2)

        Utility.setSemibold(mActivity, ques_id)
        Utility.setSemibold(mActivity, txt1)
        Utility.setSemibold(mActivity, time_left_id)




/*
        progressbar?.setProgress(i)
        mCountDownTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.v("Log_tag", "Tick of Progress$i$millisUntilFinished")
                i++
                progressbar?.setProgress(i * 100 / (7000 / 1000))
            }

            override fun onFinish() {
                //Do what you want
                i++
                progressbar?.setProgress(100)
                isTimeUp = true
            }
        }
        (mCountDownTimer as CountDownTimer).start()

        */


        mode = arguments?.getString("mode")!!

        Log.e("mode ","mode "+mode);

        if (mode.equals("rapid")) {


            Log.e("mode ","mode "+mode);
            if (Utility.isOnline(mActivity)) {

                Utility.showProgressDialog(mActivity)
                predictThePictureQuesViewModel.getPredictThePicture(mActivity, mode)
                    ?.observe(requireActivity(), Observer { predictThePictureResponseData ->
                        if (!isGetPredictPictureShown) {

                            Utility.hideProgressDialog(mActivity)
                            idQues = predictThePictureResponseData?.id!!
                            Log.e("Size", "Size " + predictThePictureResponseData?.pictures?.size);
                            totalNumberOfQuestions = predictThePictureResponseData?.pictures?.size!!
                            updateUI1(predictThePictureResponseData)
                            isGetPredictPictureShown = true
                        }

                    })

            } else {

                Utility.showDialog(
                    mActivity,
                    "Network Error !!",
                    "Please check your network connection.",
                    "Ok",
                    "Cancel"
                )

            }


        } else {

            if (Utility.isOnline(mActivity)) {

                Utility.showProgressDialog(mActivity)
                predictThePictureQuesViewModel.getPredictThePictureCalmMode(mActivity, mode)
                    ?.observe(requireActivity(), Observer { predictThePictureResponseData ->

                        if (!isGetPredictPictureShown) {


                            if(predictThePictureResponseData!!.pictures.size > 0){


                                var timer1 : Int = 0

                                Utility.hideProgressDialog(mActivity)
                                timerCalm = object : CountDownTimer(7000, 1000) {
                                    override fun onTick(millisUntilFinished: Long) {


                                        timer1++
                                        progressbar?.setProgress(timer1 * 100 / (7000 / 1000))

                                    }

                                    override fun onFinish() {


                                        Handler().postDelayed({
                                            //doSomethingHere()
                                            answerObject.clear()
                                            postAnswerRapidMode()
                                            timerCalm.cancel()

                                        }, 3000)




                                    }


                                }.start()



                                idQues = predictThePictureResponseData?.id!!
                                isGetPredictPictureShown = true

                                var x: ArrayList<PredictThePictureOptions> =
                                    predictThePictureResponseData!!.pictures[0].pictures


                                if(predict_recyclerView != null){


                                    val layoutManager = GridLayoutManager(mActivity, 2)
                                    predict_recyclerView.layoutManager = layoutManager
                                    predict_recyclerView.setAdapter(null)
                                    predict_recyclerView!!.setAdapter(
                                        RecyclerViewAdapter(
                                            predictThePictureResponseData.pictures[0].answer,
                                            mActivity,
                                            x,
                                            object : RecyclerViewAdapter.OnItemClickListener {

                                                override fun onItemClick(
                                                    predictThePictureOptions: PredictThePictureOptions?,
                                                    gyro: ArrayList<Float>?,
                                                    coordinates: ArrayList<Double>?,
                                                    pressure: Float?
                                                ) {

                                                    val predictThePictureRapidAnswerInputData: PredictThePictureRapidAnswerInputData =
                                                        PredictThePictureRapidAnswerInputData(
                                                            predictThePictureResponseData.pictures[questionCount - 1].setNum,
                                                            predictThePictureOptions?.position,
                                                            coordinates,
                                                            gyro,
                                                            pressure

                                                        )
                                                    answerObject.clear()
                                                    answerObject?.add(predictThePictureRapidAnswerInputData)
                                                    postAnswerRapidMode()
                                                    timerCalm.cancel()

                                                }

                                            })
                                    )
                                }






                            }else{

                                Utility.hideProgressDialog(mActivity)
                                Utility.showDialog(mActivity,"Error","No Question Available","Ok","cancel")
                            }

                        }

                    })

            } else {

                Utility.showDialog(
                    mActivity,
                    "Network Error !!",
                    "Please check your network connection.",
                    "Ok",
                    "Cancel"
                )

            }


        }

    }

    private fun updateUI1(predictThePictureResponseData: PredictThePictureResponseData) {

        Log.e("DATA ", "DATA " + predictThePictureResponseData);
        Log.e("DATA ", "DATA " + totalNumberOfQuestions);
        var isClicked: Boolean = false
        var isDisplayed: Boolean = false


        var timer1: Int = 0
        timer = object : CountDownTimer(7000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (!isDisplayed) {

                    isDisplayed = true

                    if (questionCount < totalNumberOfQuestions) {

                        selectedAnswer = ""
                        totalNumberOfOptionsInOneQuestion =
                            predictThePictureResponseData.pictures[questionCount - 1].pictures.size
                        totalNumberOfOptionsInOneQuestion =
                            predictThePictureResponseData.pictures[questionCount - 1].pictures.size

                        var x: ArrayList<PredictThePictureOptions> =
                            predictThePictureResponseData.pictures[questionCount - 1].pictures

                        Collections.shuffle(x)
                        val layoutManager = GridLayoutManager(mActivity, 2)
                        if(predict_recyclerView != null){


                            predict_recyclerView.layoutManager = layoutManager
                            predict_recyclerView.setAdapter(null)
                            predict_recyclerView!!.setAdapter(
                                RecyclerViewAdapter(
                                    predictThePictureResponseData.pictures[questionCount - 1].answer,
                                    mActivity,
                                    x,
                                    object : RecyclerViewAdapter.OnItemClickListener {

                                        override fun onItemClick(
                                            predictThePictureOptions: PredictThePictureOptions?,
                                            gyro: ArrayList<Float>?,
                                            coordinates: ArrayList<Double>?,
                                            pressure: Float?
                                        ) {


                                            for (i in 0 until predict_recyclerView.getChildCount()) {

                                                val child = predict_recyclerView.getChildAt(i)

                                                if (predictThePictureResponseData.pictures[questionCount - 1].answer.equals(
                                                        predictThePictureResponseData.pictures[questionCount - 1].pictures[i].position
                                                    )
                                                ) {

                                                    Log.e("here in true", "here in true")
                                                    setBorder(child.findViewById(R.id.image_op), true)
                                                    break

                                                } else {
                                                    val child = predict_recyclerView.getChildAt(i)

                                                    Log.e("here in false", "here in false")

                                                }
                                                // do stuff with child view
                                            }


                                            selectedAnswer = predictThePictureOptions?.position
                                            val predictThePictureRapidAnswerInputData: PredictThePictureRapidAnswerInputData =
                                                PredictThePictureRapidAnswerInputData(
                                                    predictThePictureResponseData.pictures[questionCount - 1].setNum,
                                                    predictThePictureOptions?.position,
                                                    coordinates,
                                                    gyro,
                                                    pressure

                                                )
                                            Log.e(
                                                "questionCount ",
                                                "questionCount " + (questionCount - 1)
                                            );
                                            answerObject?.add(predictThePictureRapidAnswerInputData)
                                            isClicked = true
                                            timer.cancel()
                                            questionCount++

                                            Handler().postDelayed({
                                                //doSomethingHere()
                                                updateUI1(predictThePictureResponseData)

                                            }, 1000)
                                            Log.e("Here in click", "Here in click @@")
                                            Log.e("Here in click", "Here in click @@ " + isClicked)

                                        }

                                    })
                            )
                        }

                    } else {

                        postAnswerRapidMode()
                        timer.cancel()
                    }
                }

                timer1++
                progressbar?.setProgress(timer1 * 100 / (7000 / 1000))
            }


            override fun onFinish() {

                progressbar?.setProgress(100)
                if (answerObject.size == questionCount - 1) {


                } else {


                    val predictThePictureRapidAnswerInputData: PredictThePictureRapidAnswerInputData =
                        PredictThePictureRapidAnswerInputData(
                            "",
                            "",
                            null,
                            null,
                            0F

                        )

                    answerObject?.add(predictThePictureRapidAnswerInputData)
                    /* */

                }
                questionCount++
                Log.e("answerObject ", "answerObject " + answerObject.toString())
                updateUI1(predictThePictureResponseData)
            }
        }.start()
    }


    fun setBorder(circularImageView: CircleImageView, selected: Boolean) {

        // Set Border
        if (selected) {
            circularImageView.setBorderColor(resources.getColor(R.color.right_ans))
            circularImageView.setBorderWidth(10)
        } else {
            circularImageView.setBorderColor(resources.getColor(R.color.wrong_ans))
            circularImageView.setBorderWidth(10)

        }
    }


    fun postAnswerRapidMode() {

        if (Utility.isOnline(mActivity)) {

            Utility.showProgressDialog(mActivity)
            predictThePictureQuesViewModel.postPredictThePictureRapidAnswer(
                mActivity,
                idQues,
                answerObject
            )?.observe(requireActivity(), Observer { predictThePictureResponseData ->

                if (predictThePictureResponseData?.message.equals("Answers succesfully recorded.")) {
                    Utility.hideProgressDialog(mActivity)
                    val alert = ViewDialog()
                    alert.showDialog(
                        activity,
                        predictThePictureResponseData?.message,
                        predictThePictureResponseData?.stats?.currencies?.rubies,
                        predictThePictureResponseData?.stats?.currencies?.emeralds,
                        predictThePictureResponseData?.sessionPoints,
                        navController
                    )
                } else {


                   // val jsonObject = JSONObject(response.errorBody()?.string())
                    /*var msg: String = ""
                    Log.e("jsonObject ","jsonObject "+jsonObject);
*/
                    /*if(jsonObject.has("message")){*/

                       // msg = (jsonObject.getString("message"))
                        Utility.hideProgressDialog(mActivity)
                        Utility.showDialog( mActivity,"Error !!",""+predictThePictureResponseData?.message,"Ok","Cancel")
                  /*  }else{

                        Utility.hideProgressDialog(mActivity)
                        Utility.showDialog( mActivity,"Error !!","Server Error.","Ok","Cancel")
                    }*/


                  /*  Utility.hideProgressDialog(mActivity)
                    Utility.showDialog(
                        mActivity,
                        "Error !!",
                        "Server Error",
                        "Ok",
                        "Cancel"
                    )*/

                }
            })

        } else {

            Utility.showDialog(
                mActivity,
                "Network Error !!",
                "Please check your network connection.",
                "Ok",
                "Cancel"
            )

        }


    }


    class ViewDialog {
        fun showDialog(activity: Activity?, msg: String?, gem: String?, emerland: String?,sessionPoint: String?,navController: NavController) {
            val dialog =
                Dialog(activity as Context, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            //Dialog dialog=new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen)

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.getWindow()?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            );
            dialog.getWindow()?.statusBarColor = Color.WHITE

            dialog.setCancelable(false)
            dialog.setContentView(R.layout.predict_the_picture_final_result_rapid_mode)

            val result1_tv = dialog.findViewById(R.id.result1_tv) as TextView
            val txt1 = dialog.findViewById(R.id.txt1) as TextView
            Utility.setSemibold(activity, txt1)
            val wow_txt = dialog.findViewById(R.id.wow_txt) as TextView
            Utility.setSemibold(activity, wow_txt)


            if(sessionPoint?.toInt()!! > 0){

                wow_txt.text = "WOW"
                result1_tv.text = "BROVO"

            }else{

                wow_txt.text = "SORRY"
                result1_tv.text = ""


            }


            val result2_tv = dialog.findViewById(R.id.result2_tv) as TextView
            result2_tv.text = msg
            Utility.setSemibold(activity, result2_tv)

            Utility.setRegular(activity, result1_tv)

            val result4_tv = dialog.findViewById(R.id.result4_tv) as TextView
            result4_tv

            val txt3 = dialog.findViewById(R.id.txt3) as TextView
            txt3.text = gem

            Utility.setSemibold(activity, txt3)

            val txt5 = dialog.findViewById(R.id.txt5) as TextView
            txt5.text = emerland
            Utility.setSemibold(activity, txt5)

            val dialogButton: TextView = dialog.findViewById(R.id.continue_btn) as TextView
            dialogButton.setOnClickListener(View.OnClickListener { dialog.dismiss()

                val action = PredictThePictureQuestionFragmentDirections.actionPredictThePictureIntroToHomescreen()
                navController.navigate(action)

            })
            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}