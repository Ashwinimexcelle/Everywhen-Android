package com.mexcelle.thoughtifydemo.ui

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.NowAndThenQuesFragmentBinding
import com.mexcelle.thoughtifydemo.model.NowAndThenAnswerInputData
import com.mexcelle.thoughtifydemo.ui.adapter.NowAndThenOptionsRecyclerView
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import com.mexcelle.thoughtifydemo.viewModel.NowAndThenQuesViewModel
import kotlinx.android.synthetic.main.fragment_now_and_then_ques.*


class NowAndThenQuesFragment : Fragment() {

    private lateinit var nowAndThenQuesViewModel: NowAndThenQuesViewModel
    private var _binding: NowAndThenQuesFragmentBinding? = null
    lateinit var mActivity: Activity
    private lateinit var navController: NavController
    var mProgressBar: ProgressBar? = null
    var mCountDownTimer: CountDownTimer? = null
    var i = 0
    var isTimeUp: Boolean = false
    var idQues: String = ""
    var isGetNowAndThenShown: Boolean = false


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        nowAndThenQuesViewModel = ViewModelProvider(this).get(
            NowAndThenQuesViewModel::class.java
        )
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_now_and_then_ques, container, false
        )
        binding.setLifecycleOwner(this)
        binding.nowAndThenQuesViewModel = nowAndThenQuesViewModel
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

        Utility.setSemibold(mActivity, txt1)
        Utility.setSemibold(mActivity, time_left_id)
        Utility.setSemibold(mActivity, ques_id)
        Utility.setRegular(mActivity, txt2)

        if (Constants.nowAndThenResponseData?.questions?.size!! > 0) {

            ques_id.text = Constants.nowAndThenResponseData?.questions!![0]?.title
            Log.e("options", "options " + Constants.nowAndThenResponseData?.questions!![0].options);

            val llm = LinearLayoutManager(mActivity)
            llm.orientation = LinearLayoutManager.VERTICAL

            _now_and_then_recyclerView.setAdapter(null)
            _now_and_then_recyclerView.setLayoutManager(llm);
            _now_and_then_recyclerView!!.setAdapter(
                NowAndThenOptionsRecyclerView(
                    mActivity,
                    Constants.nowAndThenResponseData?.questions!![0].options,
                    object : NowAndThenOptionsRecyclerView.OnItemClickListener {
                        override fun onItemClick(answer: String?) {

                            val nowAndThenAnswerInputData = NowAndThenAnswerInputData(answer!!)
                            postNowAndThenAnswer(
                                nowAndThenAnswerInputData,
                                Constants.nowAndThenResponseData?.questions!![0].id
                            )

                        }

                    })
            )


            progressbar?.setProgress(i)
            mCountDownTimer = object : CountDownTimer(7000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    Log.v("Log_tag", "Tick of Progress$i$millisUntilFinished")
                    i++
                    progressbar?.setProgress(i * 100 / (7000 / 1000))
                }

                override fun onFinish() {

                    i++
                    progressbar?.setProgress(100)
                    isTimeUp = true

                }
            }
            (mCountDownTimer as CountDownTimer).start()
        }
    }

    private fun postNowAndThenAnswer(
        nowAndThenAnswerInputData: NowAndThenAnswerInputData,
        id: String
    ) {

        if (Utility.isOnline(mActivity)) {

            Utility.showProgressDialog(mActivity)
            nowAndThenQuesViewModel.postNowAndThenQuestion(mActivity, id, nowAndThenAnswerInputData)
                ?.observe(requireActivity(), Observer { nowAndThenAnswerResponseData ->

                    Utility.hideProgressDialog(mActivity)
                    if (nowAndThenAnswerResponseData?.answerId?.length!! > 0) {
                        Utility.hideProgressDialog(mActivity)
                        val alert = ViewDialog1()
                        alert.showDialog(
                            activity, "Answer Recorded Successfully",
                            nowAndThenAnswerResponseData?.stats?.currencies?.rubies,
                            nowAndThenAnswerResponseData?.stats?.currencies?.emeralds,
                            nowAndThenAnswerResponseData?.sessionPoints, navController
                        )
                    } else {

                        Utility.hideProgressDialog(mActivity)
                        Utility.showDialog(mActivity, "Error !!", "Error", "Ok", "Cancel")

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
        fun showDialog(
            activity: Activity?,
            msg: String?,
            gem: String?,
            emerland: String?,
            sessionPoint: String?,
            navController: NavController
        ) {
            val dialog =
                Dialog(activity as Context, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            //Dialog dialog=new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen)

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.getWindow()?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            );
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.predict_the_picture_final_result_rapid_mode)
            dialog.getWindow()?.statusBarColor = Color.WHITE

            val result1_tv = dialog.findViewById(R.id.result1_tv) as TextView
            val txt1 = dialog.findViewById(R.id.txt1) as TextView
            Utility.setSemibold(activity, txt1)
            val wow_txt = dialog.findViewById(R.id.wow_txt) as TextView
            Utility.setSemibold(activity, wow_txt)


            val result2_tv = dialog.findViewById(R.id.result2_tv) as TextView
            result2_tv.text = msg
            Utility.setSemibold(activity, result2_tv)

            Utility.setRegular(activity, result1_tv)

            val result4_tv = dialog.findViewById(R.id.result4_tv) as TextView

            if (sessionPoint?.toInt()!! > 0) {

                wow_txt.text = "WOW"
                result1_tv.text = "BROVO"

            } else {

                wow_txt.text = "SORRY"
                result1_tv.text = ""


            }

            val txt3 = dialog.findViewById(R.id.txt3) as TextView
            txt3.text = gem

            Utility.setSemibold(activity, txt3)

            val txt5 = dialog.findViewById(R.id.txt5) as TextView
            txt5.text = emerland
            Utility.setSemibold(activity, txt5)

            val dialogButton: TextView = dialog.findViewById(R.id.continue_btn) as TextView
            dialogButton.setOnClickListener(View.OnClickListener {
                dialog.dismiss()

                val action =
                    NowAndThenQuesFragmentDirections.actionPredictThePictureIntroToHomescreen()
                navController.navigate(action)

            })
            dialog.show()
        }
    }


    class ViewDialog1 {
        fun showDialog(
            activity: Activity?,
            msg: String?,
            gem: String?,
            emerland: String?,
            sessionPoint: String?,
            navController: NavController
        ) {
            val dialog =
                Dialog(activity as Context, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            //Dialog dialog=new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen)

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.getWindow()?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            );
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.predict_the_picture_final_result_rapid_mode)
            dialog.getWindow()?.statusBarColor = Color.WHITE

            val result1_tv = dialog.findViewById(R.id.result1_tv) as TextView
            val txt1 = dialog.findViewById(R.id.txt1) as TextView
            Utility.setSemibold(activity, txt1)
            val wow_txt = dialog.findViewById(R.id.wow_txt) as TextView
            Utility.setSemibold(activity, wow_txt)


            val result2_tv = dialog.findViewById(R.id.result2_tv) as TextView
            result2_tv.text = msg
            Utility.setSemibold(activity, result2_tv)

            Utility.setRegular(activity, result1_tv)

            val result4_tv = dialog.findViewById(R.id.result4_tv) as TextView

            wow_txt.text = "WOW"
            result1_tv.text = "BROVO"


            val txt3 = dialog.findViewById(R.id.txt3) as TextView
            txt3.text = gem

            Utility.setSemibold(activity, txt3)

            val txt5 = dialog.findViewById(R.id.txt5) as TextView
            txt5.text = emerland
            Utility.setSemibold(activity, txt5)

            val dialogButton: TextView = dialog.findViewById(R.id.continue_btn) as TextView
            dialogButton.setOnClickListener(View.OnClickListener {
                dialog.dismiss()

                val action =
                    NowAndThenQuesFragmentDirections.actionPredictThePictureIntroToHomescreen()
                navController.navigate(action)

            })
            dialog.show()
        }
    }


}