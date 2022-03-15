package com.mexcelle.everywhendemo.ui.ui.home

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.databinding.HomeFragmentBinding
import com.mexcelle.everywhendemo.util.Utility
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    lateinit var mActivity: Activity
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: HomeFragmentBinding? = null
    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        binding.setLifecycleOwner(this)
        binding.homeViewModel = homeViewModel


        // Utility.setRegular(activity,txt1)
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

        Utility.setSemibold(mActivity, pp_txt1)
        Utility.setRegular(mActivity, pp_txt2)
        Utility.setSemibold(mActivity, pp_play_now)
        Utility.setSemibold(mActivity, nowthen_txt1)
        Utility.setRegular(mActivity, nowthen_txt2)
        Utility.setSemibold(mActivity, nowthen_play_now)
        Utility.setRegular(mActivity, txt1)
        Utility.setSemibold(mActivity, txt2)

        pp_play_now!!.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToPredict()
            navController.navigate(action)

        }

        nowthen_play_now!!.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToNow()
            navController.navigate(action)

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}