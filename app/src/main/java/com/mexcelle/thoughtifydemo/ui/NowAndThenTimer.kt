package com.mexcelle.thoughtifydemo.ui

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.NowAndThenTimerBinding
import com.mexcelle.thoughtifydemo.model.NowAndThenResponseData
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import com.mexcelle.thoughtifydemo.viewModel.NowAndThenTimerViewModel
import kotlinx.android.synthetic.main.fragment_now_and_then_intro.*
import kotlinx.android.synthetic.main.fragment_now_and_then_ques.*
import kotlinx.android.synthetic.main.fragment_now_and_then_timer.*

class NowAndThenTimer : Fragment() {

    private lateinit var nowAndThenTimerViewModel: NowAndThenTimerViewModel
    private var _binding: NowAndThenTimerBinding? =  null
    lateinit var mActivity: Activity
    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var mCountDownTimer: CountDownTimer? = null
    var i = 0
    var isTimeUp: Boolean = false
    public var nowAndThenResponseData: NowAndThenResponseData? = null
    private var angle = 0f
    var isGetNowAndThenShown: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        nowAndThenTimerViewModel = ViewModelProvider(this).get(NowAndThenTimerViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_now_and_then_timer, container, false
        )
        binding.setLifecycleOwner(this)
        binding.nowAndThenTimerViewModel = nowAndThenTimerViewModel

        //init()
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(isAdded && requireActivity() != null){

            if(activity != null){
                mActivity = requireActivity()
                Log.e("","here in is added");
                init()

            }

        }else{

            Log.e("","here in is not added");

        }
        Log.e("in INIT","in INIT activity created");


    }


    private fun init() {

        val window = mActivity?.window
        navController = findNavController()

        Utility.setRegular(mActivity!!,text_1)
        Utility.setRegular(mActivity!!,timer1)
        Constants.nowAndThenResponseData = null

        if (Utility.isOnline(mActivity)) {

            Utility.showProgressDialog(mActivity)
            nowAndThenTimerViewModel.getNowAndThenQuestion(mActivity)
                ?.observe(requireActivity(), Observer { nowAndThenResponseData ->
                    Utility.hideProgressDialog(mActivity)

                    if(!isGetNowAndThenShown) {

                        if (nowAndThenResponseData?.questions?.size!! > 0) {

                            //ques_id.text = nowAndThenResponseData.questions[0].title
                            nav_btn.playAnimation()
                            Log.e("options","options "+nowAndThenResponseData.questions[0].options);
                            text_1.text = nowAndThenResponseData.questions[0].title
                            this.nowAndThenResponseData = nowAndThenResponseData
                            Constants.nowAndThenResponseData = nowAndThenResponseData




                            mCountDownTimer = object : CountDownTimer(10000, 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    Log.v("Log_tag", "Tick of Progress$i$millisUntilFinished")
                                    i++
                                    angle += 90f
                                    imageview_timer1.animate().setDuration(180).rotation(angle).start()
                                    //timer1.text = i.toString()
                                    //progressbar?.setProgress(i * 100 / (7000 / 1000))
                                }

                                override fun onFinish() {
                                    //Do what you want
                                    i++

                                    val action = NowAndThenTimerDirections.actionNowAndThenTimerFragmentToNowAndThenQuesFragment()
                                    navController.navigate(action)

                                    /*progressbar?.setProgress(100)
                                    isTimeUp = true*/
                                }
                            }
                            (mCountDownTimer as CountDownTimer).start()




                        } else {


                            /*mCountDownTimer.cancel()*/

                            nav_btn.pauseAnimation()


                            Utility.showDialog(
                                mActivity,
                                "No Questions Available !!",
                                "No Questions Available.",
                                "Ok",
                                "Cancel"
                            )

                        }

                        isGetNowAndThenShown = true
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






        /* Utility.setSemibold(mActivity!!,name)
         Utility.setSemibold(mActivity!!,add_friend)
         Utility.setSemibold(mActivity!!,awards_tv)
         Utility.setRegular(mActivity!!,location_title_txt)
         Utility.setRegular(mActivity!!,location_txt)
         Utility.setRegular(mActivity!!,ethinicity_title_tv)
         Utility.setRegular(mActivity!!,ethinicity_tv)
         Utility.setRegular(mActivity!!,age_title_tv)
         Utility.setRegular(mActivity!!,age_tv)
         Utility.setSemibold(mActivity!!,txt55)
         Utility.setSemibold(mActivity!!,leader_board_title_tv)
         Utility.setRegular(mActivity!!,leader_board_analysis_title_tv)
         Utility.setRegular(mActivity!!,leader_board_analysis_title_tv)
         Utility.setRegular(mActivity!!,leader_board_analysis_title_tv)
         Utility.setRegular(mActivity!!,leaderboard_score_tv)
         Utility.setRegular(mActivity!!,leaderboard_analysis_tv)*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}