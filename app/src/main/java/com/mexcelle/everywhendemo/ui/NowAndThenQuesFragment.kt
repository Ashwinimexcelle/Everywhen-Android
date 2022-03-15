package com.mexcelle.everywhendemo.ui

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.databinding.NowAndThenQuesFragmentBinding
import com.mexcelle.everywhendemo.model.NowAndThenAnswerInputData
import com.mexcelle.everywhendemo.model.NowAndThenInputData
import com.mexcelle.everywhendemo.model.PredictThePictureCalmAnswerInputData
import com.mexcelle.everywhendemo.ui.adapter.NowAndThenOptionsRecyclerView
import com.mexcelle.everywhendemo.util.Constants
import com.mexcelle.everywhendemo.util.Utility
import com.mexcelle.everywhendemo.viewModel.NowAndThenQuesViewModel
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


        ques_id.text = Constants.nowAndThenResponseData?.questions!![0]?.title
        Log.e("options","options "+Constants.nowAndThenResponseData?.questions!![0].options);

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
                        postNowAndThenAnswer(nowAndThenAnswerInputData,Constants.nowAndThenResponseData?.questions!![0].id)

                    }

                }))
        
        

/*
        if (Utility.isOnline(mActivity)) {

            Utility.showProgressDialog(mActivity)
            nowAndThenQuesViewModel.getNowAndThenQuestion(mActivity)
                ?.observe(requireActivity(), Observer { nowAndThenResponseData ->

                    Utility.hideProgressDialog(mActivity)

                    if (nowAndThenResponseData?.questions?.size!! > 0) {

                        ques_id.text = nowAndThenResponseData.questions[0].title
                        Log.e("options","options "+nowAndThenResponseData.questions[0].options);

                        val llm = LinearLayoutManager(mActivity)
                        llm.orientation = LinearLayoutManager.VERTICAL

                        _now_and_then_recyclerView.setAdapter(null)
                        _now_and_then_recyclerView.setLayoutManager(llm);
                        _now_and_then_recyclerView!!.setAdapter(
                            NowAndThenOptionsRecyclerView(
                                mActivity,
                                nowAndThenResponseData.questions[0].options,
                                object : NowAndThenOptionsRecyclerView.OnItemClickListener {
                                    override fun onItemClick(answer: String?) {

                                        val nowAndThenAnswerInputData = NowAndThenAnswerInputData(answer!!)
                                        postNowAndThenAnswer(nowAndThenAnswerInputData,nowAndThenResponseData.questions[0].id)





                                    }

                                }))

                    } else {


                        Utility.showDialog(
                            mActivity,
                            "No Questions Available !!",
                            "No Questions Available.",
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

        }*/

        /*progressbar?.setProgress(i)
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
        (mCountDownTimer as CountDownTimer).start()*/
    }

    private fun postNowAndThenAnswer(nowAndThenAnswerInputData: NowAndThenAnswerInputData,
                                     id :String) {

        if (Utility.isOnline(mActivity)) {

            Utility.showProgressDialog(mActivity)
            nowAndThenQuesViewModel.postNowAndThenQuestion(mActivity,id,nowAndThenAnswerInputData)
                ?.observe(requireActivity(), Observer { nowAndThenAnswerResponseData ->

                    Utility.hideProgressDialog(mActivity)


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