package com.mexcelle.everywhendemo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.util.Utility
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.activity_terms_and_conditions.*

class TermsAndConditionsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_conditions)

        Init()

    }

    private fun Init() {

        Utility.setSemibold(this@TermsAndConditionsActivity,terms_and_conditions_title)
        Utility.setRegular(this@TermsAndConditionsActivity,terms_txt)
        Utility.setRegular(this@TermsAndConditionsActivity,accept_cb)
        Utility.setRegular(this@TermsAndConditionsActivity,terms_continue_txt2)

        terms_continue_txt2!!.setOnClickListener {
            goToPrivacyPolicy()
        }


    }


    private fun goToPrivacyPolicy() {

        val intent = Intent(this@TermsAndConditionsActivity, PrivacyPolicyActivity::class.java)
        startActivity(intent)
    }
}