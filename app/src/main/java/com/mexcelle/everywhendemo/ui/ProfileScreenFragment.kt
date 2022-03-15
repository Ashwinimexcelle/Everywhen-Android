package com.mexcelle.everywhendemo.ui

import android.app.Activity
import android.content.Intent
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
import com.mexcelle.everywhendemo.databinding.ProfileScreenFragmentBinding
import com.mexcelle.everywhendemo.ui.ui.home.HomeFragmentDirections
import com.mexcelle.everywhendemo.util.Utility
import com.mexcelle.everywhendemo.viewModel.ProfileScreenViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile_screen.*

class ProfileScreenFragment : Fragment() {

    private lateinit var profileScreenViewModel: ProfileScreenViewModel
    private var _binding: ProfileScreenFragmentBinding? =  null
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

        profileScreenViewModel = ViewModelProvider(this).get(ProfileScreenViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile_screen, container, false
        )
        binding.setLifecycleOwner(this)
        binding.profileScreenViewModel = profileScreenViewModel

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


        navController = findNavController()


        val window = mActivity?.window

        Utility.setSolidFontAwesome(mActivity!!,edit_tv)
        Utility.setSemibold(mActivity!!,name)
        Utility.setSemibold(mActivity!!,add_friend)
        Utility.setSemibold(mActivity!!,awards_tv)
        Utility.setRegular(mActivity!!,location_title_txt)
        Utility.setRegular(mActivity!!,location_txt)
        Utility.setRegular(mActivity!!,ethinicity_title_tv)
        Utility.setRegular(mActivity!!,ethinicity_tv)
        Utility.setRegular(mActivity!!,age_title_tv)
        Utility.setRegular(mActivity!!,age_tv)
        Utility.setSemibold(mActivity!!,txt55)
        Utility.setSemibold(mActivity!!,txt45)

        Utility.setSemibold(mActivity!!,leader_board_title_tv)
        Utility.setRegular(mActivity!!,leader_board_analysis_title_tv)
        Utility.setRegular(mActivity!!,leader_board_analysis_title_tv)
        Utility.setRegular(mActivity!!,leader_board_analysis_title_tv)
        Utility.setRegular(mActivity!!,leaderboard_score_tv)
        Utility.setRegular(mActivity!!,leaderboard_analysis_tv)

        Utility.setRegular(mActivity!!,monthly_score_title_tv)
        Utility.setRegular(mActivity!!,weekly_score_title_tv)
        Utility.setRegular(mActivity!!,daily_score_title_tv)
        Utility.setRegular(mActivity!!,monthly_score_per_tv)
        Utility.setRegular(mActivity!!,weekly_score_per_tv)
        Utility.setRegular(mActivity!!,daily_score_per_tv)

        Utility.setbold(mActivity!!,monthly_score_tv)
        Utility.setbold(mActivity!!,weekly_score_tv)
        Utility.setbold(mActivity!!,daily_score_tv)


        /*val intent = Intent(mActivity!!, HomeScreenActivity::class.java)
        startActivity(intent)



*/
        edit_tv!!.setOnClickListener {

            val action = ProfileScreenFragmentDirections.actionProfilescreenfragmentToProfilefragment()
            navController.navigate(action)

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}