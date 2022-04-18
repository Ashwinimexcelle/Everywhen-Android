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
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.FriendsFragmentBinding
import com.mexcelle.thoughtifydemo.util.Utility
import com.mexcelle.thoughtifydemo.viewModel.FriendsViewModel
import kotlinx.android.synthetic.main.fragment_friends.*

class FriendsFragment: Fragment() {

    private lateinit var friendsViewModel: FriendsViewModel
    private var _binding: FriendsFragmentBinding? =  null
    lateinit var mActivity: Activity

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        friendsViewModel = ViewModelProvider(this).get(FriendsViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_friends, container, false
        )
        binding.setLifecycleOwner(this)
        binding.friendsViewModel = friendsViewModel

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
        Utility.setSemibold(mActivity!!,leader_board_title_tv)
        Utility.setRegular(mActivity!!,leader_board_analysis_title_tv)
        Utility.setRegular(mActivity!!,leader_board_analysis_title_tv)
        Utility.setRegular(mActivity!!,leader_board_analysis_title_tv)
        Utility.setRegular(mActivity!!,leaderboard_score_tv)
        Utility.setRegular(mActivity!!,leaderboard_analysis_tv)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}