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
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.databinding.ProfileFragmentBinding
import com.mexcelle.everywhendemo.util.Utility
import com.mexcelle.everywhendemo.viewModel.ProfileViewModel

import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: ProfileFragmentBinding? =  null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var mActivity: Activity


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile, container, false
        )
        binding.setLifecycleOwner(this)
        binding.profileViewModel = profileViewModel

       // init()
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

        val window = mActivity.window


        Utility.setSemibold(mActivity,continue_txt)
        Utility.setRegular(mActivity,location_tv)
        Utility.setRegular(mActivity,location_tv1)
        Utility.setRegular(mActivity,ethincity_title_tv)
        Utility.setRegular(mActivity,age_title_tv)
        Utility.setRegular(mActivity,gender_title_tv)
        Utility.setRegular(mActivity,describe_tv)
        Utility.setRegular(mActivity,age_et)
        Utility.setRegular(mActivity,describe_ed)
        Utility.setRegular(mActivity,ancestral_tv)
        Utility.setRegular(mActivity,ancestral_tv)
        Utility.setRegular(mActivity,ed_t1)
        Utility.setSemibold(mActivity,ed_t2)

        Utility.setRegular(mActivity,notification_id)
        Utility.setRegular(mActivity,location_switch_id)
        Utility.setSemibold(mActivity,invite_friends_txt)
        Utility.setRegular(mActivity,name_tv)
        Utility.setRegular(mActivity,name_ed)
        Utility.setSolidFontAwesome(mActivity,location_icon_tv)
        Utility.setSolidFontAwesome(mActivity,location_icon)
        Utility.setSolidFontAwesome(mActivity,ethinicity_icon_tv)
        Utility.setSolidFontAwesome(mActivity,age_icon)
        Utility.setSolidFontAwesome(mActivity,gender_icon_tv)
        Utility.setSolidFontAwesome(mActivity,accenstral_heritage_icon_tv)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}