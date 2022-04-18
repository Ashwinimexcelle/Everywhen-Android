package com.mexcelle.thoughtifydemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.ForgotPasswordFragmentBinding
import com.mexcelle.thoughtifydemo.databinding.OTPActivityBinding
import com.mexcelle.thoughtifydemo.viewModel.ForgotPasswordViewModel
import com.mexcelle.thoughtifydemo.viewModel.OTPViewModel

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var binding: ForgotPasswordFragmentBinding
    lateinit var forgotPasswordViewModel: ForgotPasswordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        forgotPasswordViewModel = ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.chnage_password_activity)
        binding.setLifecycleOwner(this)
        binding.forgotPasswordViewModel = forgotPasswordViewModel

    }
}