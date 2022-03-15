package com.mexcelle.everywhendemo.ui

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.databinding.PredictThePictureQuesFragmentBinding
import com.mexcelle.everywhendemo.model.PredictThePictureCalmAnswerInputData
import com.mexcelle.everywhendemo.model.PredictThePictureOptions
import com.mexcelle.everywhendemo.model.PredictThePictureRapidAnswerInputData
import com.mexcelle.everywhendemo.model.PredictThePictureResponseData
import com.mexcelle.everywhendemo.ui.adapter.RecyclerViewAdapter
import com.mexcelle.everywhendemo.util.Utility
import com.mexcelle.everywhendemo.viewModel.PredictThePictureQuesViewModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_predict_the_picture_ques.*


class PredictThePictureQuestionFragment : Fragment() {

    private lateinit var predictThePictureQuesViewModel: PredictThePictureQuesViewModel
    private var _binding: PredictThePictureQuesFragmentBinding? = null
    lateinit var mActivity: Activity
    private lateinit var navController: NavController
    var mProgressBar: ProgressBar? = null
    var mCountDownTimer: CountDownTimer? = null
    var isTimeUp: Boolean = false
    var mode: String = "rapid"
    var idQues: String = ""
    var totalNumberOfQuestions: Int = 0
    var totalNumberOfOptionsInOneQuestion: Int = 0
    var questionCounter: Int = 0
    var answerArray: ArrayList<String>? = null
    var selectedAnswer: String? = null
    var answerObject: ArrayList<PredictThePictureRapidAnswerInputData?> = ArrayList<PredictThePictureRapidAnswerInputData?>()
    var questionCount: Int = 1
    var isGetPredictPictureShown: Boolean = false
    private lateinit var timer: CountDownTimer



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

        if (Utility.isOnline(mActivity)) {

            Utility.showProgressDialog(mActivity)
            predictThePictureQuesViewModel.getPredictThePicture(mActivity, mode)
                ?.observe(requireActivity(), Observer { predictThePictureResponseData ->
                    if(!isGetPredictPictureShown) {

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
    }

    private fun updateUI1(predictThePictureResponseData: PredictThePictureResponseData) {

        Log.e("DATA ", "DATA " + predictThePictureResponseData);
        Log.e("DATA ", "DATA " + totalNumberOfQuestions);
        var isClicked :Boolean = false
        var isDisplayed :Boolean = false


        timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                if(!isDisplayed){

                    isDisplayed = true

                    if (questionCount < totalNumberOfQuestions) {

                        selectedAnswer = ""
                        totalNumberOfOptionsInOneQuestion =
                            predictThePictureResponseData.pictures[questionCount - 1].pictures.size
                        totalNumberOfOptionsInOneQuestion =
                            predictThePictureResponseData.pictures[questionCount - 1].pictures.size

                        val layoutManager = GridLayoutManager(mActivity, 2)
                        recyclerView.layoutManager = layoutManager
                        recyclerView.setAdapter(null)
                        recyclerView!!.setAdapter(
                            RecyclerViewAdapter(
                                predictThePictureResponseData.pictures[questionCount - 1].answer,
                                mActivity,
                                predictThePictureResponseData.pictures[questionCount - 1].pictures,
                                object : RecyclerViewAdapter.OnItemClickListener {

                                    override fun onItemClick(
                                        predictThePictureOptions: PredictThePictureOptions?,
                                        gyro: ArrayList<Float>?,
                                        coordinates: ArrayList<Double>?,
                                        pressure: Float?
                                    ) {


                                        for (i in 0 until recyclerView.getChildCount()) {

                                            val child = recyclerView.getChildAt(i)

                                            if(predictThePictureResponseData.pictures[questionCount-1].answer.equals(predictThePictureResponseData.pictures[questionCount-1].pictures[i].position)){

                                                Log.e("here in true","here in true")
                                                setBorder(child.findViewById(R.id.image_op),true)
                                                break

                                            }else{
                                                val child = recyclerView.getChildAt(i)

                                                Log.e("here in false","here in false")

                                            }
                                            // do stuff with child view
                                        }


                                        selectedAnswer = predictThePictureOptions?.position
                                        //recyclerView.getMo
                                        Log.e(
                                            "position ",
                                            "predictThePictureOptions " + predictThePictureOptions?.position
                                        );
                                        Log.e(
                                            "Correct Ans ",
                                            "predictThePictureOptions " + predictThePictureResponseData.pictures[questionCount-1].answer
                                        );

                                        val predictThePictureRapidAnswerInputData:PredictThePictureRapidAnswerInputData
                                                = PredictThePictureRapidAnswerInputData(predictThePictureResponseData.pictures[questionCount-1].setNum,
                                            predictThePictureOptions?.position,
                                            coordinates,
                                            gyro,
                                            pressure

                                        )
                                        Log.e("questionCount ","questionCount "+(questionCount-1));
                                        answerObject?.add(predictThePictureRapidAnswerInputData)
                                        isClicked = true
                                        timer.cancel()
                                        questionCount++

                                        Handler().postDelayed({
                                            //doSomethingHere()
                                            updateUI1(predictThePictureResponseData)

                                        }, 1000)
                                        Log.e("Here in click","Here in click @@")
                                        Log.e("Here in click","Here in click @@ "+isClicked)

                                    }

                                })
                        )
                        Log.e("answerObject ","answerObject "+answerObject.toString())

                        Log.e("Here in click","Here in click @@ "+isClicked)
                        Log.e(
                            "Correct Ans ",
                            "predictThePictureOptions ##" + answerObject.size
                        );
                        Log.e(
                            "Correct Ans ",
                            "predictThePictureOptions ##" + answerObject.toString()
                        );


                    }else{

                        postAnswerRapidMode()
                        timer.cancel()
                    }
                }
            }

            override fun onFinish() {

                Log.e("questionCount ", "questionCount !!!!" + questionCount);
                if(answerObject.size == questionCount - 1){


                }else{


                    val predictThePictureRapidAnswerInputData:PredictThePictureRapidAnswerInputData
                            = PredictThePictureRapidAnswerInputData("",
                        "",
                        null,
                        null,
                        0F

                    )

                    answerObject?.add(predictThePictureRapidAnswerInputData)
                   /* */

                }
                questionCount++
                Log.e("answerObject ","answerObject "+answerObject.toString())
                updateUI1(predictThePictureResponseData)
            }
        }.start()
    }


    fun setBorder(circularImageView: CircleImageView, selected: Boolean) {

        // Set Border
        if (selected) {
            circularImageView.setBorderColor(resources.getColor(R.color.right_ans))
            circularImageView.setBorderWidth(5)
        } else {
            circularImageView.setBorderColor(resources.getColor(R.color.wrong_ans))
            circularImageView.setBorderWidth(5)

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

               /* Utility.showDialog(
                    mActivity,
                    "Register !!",
                    "Success" *//*+ signupResponseData?.message*//*,
                    "Ok",
                    "Cancel"
                )
                Utility.hideProgressDialog(mActivity)
                *//* val intent = Intent(mActivity, OTPActivity::class.java)
                        startActivity(intent)*//*

*/

                if(predictThePictureResponseData?.message.equals("Answers succesfully recorded.")){

                    val alert = ViewDialog()
                    alert.showDialog(activity,predictThePictureResponseData?.message, predictThePictureResponseData?.stats?.currencies?.rubies,
                        predictThePictureResponseData?.sessionPoints)
                }else{

                    Utility.showDialog(
                        mActivity,
                        "Error !!",
                        "Server Error",
                        "Ok",
                        "Cancel"
                    )

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
        fun showDialog(activity: Activity?, msg: String?, gem: String?,emerland: String?) {
            val dialog = Dialog(activity!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.predict_the_picture_final_result_rapid_mode)

            val result1_tv = dialog.findViewById(R.id.result1_tv) as TextView
            val txt1 = dialog.findViewById(R.id.txt1) as TextView
            Utility.setSemibold(activity,txt1)


            val result2_tv = dialog.findViewById(R.id.result2_tv) as TextView
            result2_tv.text = msg
            Utility.setSemibold(activity,result2_tv)

            Utility.setRegular(activity,result1_tv)

            val result4_tv = dialog.findViewById(R.id.result4_tv) as TextView
            result4_tv


            val txt2 = dialog.findViewById(R.id.txt2) as TextView
            Utility.setSolidFontAwesome(activity,txt2)



            val txt3 = dialog.findViewById(R.id.txt3) as TextView
            txt3.text = gem

            Utility.setSemibold(activity,txt3)

            val txt4 = dialog.findViewById(R.id.txt4)as TextView
            Utility.setSolidFontAwesome(activity,txt4)


            val txt5 = dialog.findViewById(R.id.txt5) as TextView
            txt5.text = emerland
            Utility.setSemibold(activity,txt5)


            val dialogButton: TextView = dialog.findViewById(R.id.continue_btn) as TextView
            dialogButton.setOnClickListener(View.OnClickListener { dialog.dismiss() })
            dialog.show()
        }
    }

    fun postAnswerCalmMode() {
        val PredictThePictureCalmAnswerInputData =
            PredictThePictureCalmAnswerInputData("", "", emptyArray(), emptyArray())


        if (Utility.isOnline(mActivity)) {

            Utility.showProgressDialog(mActivity)
            predictThePictureQuesViewModel.postPredictThePictureCalmAnswer(
                mActivity,
                mode,
                PredictThePictureCalmAnswerInputData
            )?.observe(requireActivity(), Observer { predictThePictureResponseData ->

                Utility.showDialog(
                    mActivity,
                    "Register !!",
                    "Success" /*+ signupResponseData?.message*/,
                    "Ok",
                    "Cancel"
                )
                Utility.hideProgressDialog(mActivity)
                /* val intent = Intent(mActivity, OTPActivity::class.java)
                        startActivity(intent)*/


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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}