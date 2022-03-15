package com.mexcelle.everywhendemo.ui

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
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.databinding.PredictThePictureFragmentBinding
import com.mexcelle.everywhendemo.util.Utility
import com.mexcelle.everywhendemo.viewModel.PredictThePictureIntroViewModel
import kotlinx.android.synthetic.main.fragment_now_and_then_intro.*
import kotlinx.android.synthetic.main.fragment_predict_the_picture_intro.*

class PredictThePictureIntro: Fragment() {

    private lateinit var predictThePictureIntroViewModel: PredictThePictureIntroViewModel
    private var _binding: PredictThePictureFragmentBinding? =  null
    lateinit var mActivity: Activity
    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        predictThePictureIntroViewModel = ViewModelProvider(this).get(PredictThePictureIntroViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_predict_the_picture_intro, container, false
        )
        binding.setLifecycleOwner(this)
        binding.predictThePictureViewModel = predictThePictureIntroViewModel
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


        Utility.setRegular(mActivity,pp_txt1)
        Utility.setRegular(mActivity,pp_txt)
        Utility.setSemibold(mActivity,pp_coutinue_txt)


        pp_coutinue_txt!!.setOnClickListener {

            val action = PredictThePictureIntroDirections.actionPredictThePictureIntroToPredictThePictureQuestionFragment()
            navController.navigate(action)

        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}