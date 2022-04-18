package com.mexcelle.thoughtifydemo.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.OTPActivityBinding
import com.mexcelle.thoughtifydemo.util.Utility
import com.mexcelle.thoughtifydemo.viewModel.OTPViewModel
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

        otp_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if(otp_et.text.toString().length < 4){

                    check_tv.visibility = View.GONE

                }else{

                    check_tv.visibility = View.VISIBLE

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })






    }


}


