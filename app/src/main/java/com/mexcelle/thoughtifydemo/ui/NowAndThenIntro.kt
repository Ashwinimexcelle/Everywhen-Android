package com.mexcelle.thoughtifydemo.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.NowAndThenFragmentBinding
import com.mexcelle.thoughtifydemo.util.Utility
import com.mexcelle.thoughtifydemo.viewModel.NowAndThenOntroViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_now_and_then_intro.*

class NowAndThenIntro: Fragment() {

    private lateinit var nowAndThenOntroViewModel: NowAndThenOntroViewModel
    private var _binding: NowAndThenFragmentBinding? =  null
    private lateinit var navController: NavController
    lateinit var mActivity: Activity

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        nowAndThenOntroViewModel = ViewModelProvider(this).get(NowAndThenOntroViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_now_and_then_intro, container, false
        )
        binding.setLifecycleOwner(this)
        binding.nowAndThenOntroViewModel = nowAndThenOntroViewModel
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

        navController = findNavController()


        Utility.setRegular(mActivity,now_txt)
        Utility.setRegular(mActivity,now_txt1)
        Utility.setSemibold(mActivity,now_coutinue_txt)

        now_coutinue_txt!!.setOnClickListener {

            val action = NowAndThenIntroDirections.actionNowAndThenIntroToNowAndThenTimerFragment()
            navController.navigate(action)

        }

    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}