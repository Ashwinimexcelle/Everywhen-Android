package com.mexcelle.thoughtifydemo.ui.ui

import android.app.Activity
import android.os.Bundle
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
import com.mexcelle.thoughtifydemo.databinding.ForgotPasswordFragmentBinding
import com.mexcelle.thoughtifydemo.databinding.SettingsFragmentBinding
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import com.mexcelle.thoughtifydemo.viewModel.ForgotPasswordViewModel
import com.mexcelle.thoughtifydemo.viewModel.SettingsViewModel
import kotlinx.android.synthetic.main.chnage_password_activity.*
import kotlinx.android.synthetic.main.fragment_profile_screen.*

class SettingsFragment: Fragment() {

    private lateinit var forgotPasswordViewModel: ForgotPasswordViewModel
    private var _binding: ForgotPasswordFragmentBinding? =  null

    // This property is only valid between onCreateView and
    // onDestroyView.
    lateinit var mActivity: Activity
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        forgotPasswordViewModel = ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.chnage_password_activity, container, false
        )
        binding.setLifecycleOwner(this)
        binding.forgotPasswordViewModel = forgotPasswordViewModel
        return binding.getRoot()
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(isAdded && requireActivity() != null){

            if(activity != null){
                mActivity = requireActivity()
                Log.e("","here in is added");
                Init()

            }

        }else{

            Log.e("","here in is not added");

        }
        Log.e("in INIT","in INIT activity created");


    }

    private fun Init() {

        navController = findNavController()
        val window = mActivity?.window

        setTextFont()

        save_txt.setOnClickListener(View.OnClickListener {

            if (Utility.isOnline(mActivity)) {

                Utility.showProgressDialog(mActivity)
                forgotPasswordViewModel.changePassword(mActivity)!!
                    .observe(requireActivity(), Observer { changePasswordResponseData ->
                        Utility.hideProgressDialog(mActivity)
                        Log.e(
                            "changePasswordResponseData ",
                            "changePasswordResponseData " + changePasswordResponseData
                        );
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
        })
    }

    private fun setTextFont() {

        Utility.setSemibold(mActivity!!,change_password_title)
        Utility.setSemibold(mActivity!!,save_txt)
        Utility.setRegular(mActivity!!,old_password_txt2)
        Utility.setRegular(mActivity!!,old_password_et)
        Utility.setRegular(mActivity!!,new_password_txt2)
        Utility.setRegular(mActivity!!,new_password_et)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}