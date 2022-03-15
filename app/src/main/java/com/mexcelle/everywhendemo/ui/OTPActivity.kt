package com.mexcelle.everywhendemo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.databinding.OTPActivityBinding
import com.mexcelle.everywhendemo.databinding.RegisterActivityBinding
import com.mexcelle.everywhendemo.util.Constants
import com.mexcelle.everywhendemo.util.Utility
import com.mexcelle.everywhendemo.viewModel.OTPViewModel
import com.mexcelle.everywhendemo.viewModel.RegisterScreenViewModel
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_register.*

class OTPActivity : AppCompatActivity() {


    lateinit var binding: OTPActivityBinding
    lateinit var otpViewModel: OTPViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        otpViewModel = ViewModelProvider(this).get(OTPViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp)
        binding.setLifecycleOwner(this)
        binding.otpScreenViewModel = otpViewModel


        Init()

        Utility.setSolidFontAwesome(this@OTPActivity,check_tv)

        check_tv.setOnClickListener {

           /* val intent = Intent(this@OTPActivity, IntoductionActivity::class.java)
            startActivity(intent)*/

            if (Utility.isOnline(this@OTPActivity)) {

                Utility.showProgressDialog(this@OTPActivity)
                otpViewModel.submitOtp(this@OTPActivity)!!
                    .observe(this, Observer { otpResponseData ->

                        Utility.showDialog(
                            this,
                            "Register !!",
                            "Success" /*+ signupResponseData?.message*/,
                            "Ok",
                            "Cancel"
                        )
                        Utility.hideProgressDialog(this@OTPActivity)

                        val intent = Intent(this@OTPActivity, LoginActivity::class.java)
                        startActivity(intent)

                        //Constants.USER_OTP_TOKEN  = otpResponseData!!.otpToken


                    })

            } else {

                Utility.showDialog(
                    this,
                    "Network Error !!",
                    "Please check your network connection.",
                    "Ok",
                    "Cancel"
                )

            }


        }


    }


    private fun Init() {



        Utility.setbold(this@OTPActivity,otp_title)
        Utility.setRegular(this@OTPActivity,otp_txt1)
        Utility.setRegular(this@OTPActivity,otp_txt2)
        Utility.setRegular(this@OTPActivity,otp_et)
        Utility.setSolidFontAwesome(this@OTPActivity,check_tv)


    }


}


