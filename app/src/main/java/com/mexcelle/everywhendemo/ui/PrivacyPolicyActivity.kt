package com.mexcelle.everywhendemo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.util.Utility
import kotlinx.android.synthetic.main.activity_privacy_policy.*
import kotlinx.android.synthetic.main.activity_terms_and_conditions.*
import kotlinx.android.synthetic.main.activity_terms_and_conditions.accept_cb
import kotlinx.android.synthetic.main.activity_terms_and_conditions.terms_and_conditions_title
import kotlinx.android.synthetic.main.activity_terms_and_conditions.terms_continue_txt2
import kotlinx.android.synthetic.main.activity_terms_and_conditions.terms_txt

class PrivacyPolicyActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        Init()

    }

    private fun Init() {

        Utility.setSemibold(this@PrivacyPolicyActivity,privacy_policy_title)
        Utility.setRegular(this@PrivacyPolicyActivity,privacy_policy_txt)

        Utility.setSemibold(this@PrivacyPolicyActivity,privacy_policy_accept_txt)
        Utility.setSemibold(this@PrivacyPolicyActivity,switch1)
        Utility.setSemibold(this@PrivacyPolicyActivity,switch2)
        Utility.setSemibold(this@PrivacyPolicyActivity,switch3)

        Utility.setRegular(this@PrivacyPolicyActivity,switch1_txt)
        Utility.setRegular(this@PrivacyPolicyActivity,switch2_txt)
        Utility.setRegular(this@PrivacyPolicyActivity,switch3_txt)


        privacy_policy_accept_txt!!.setOnClickListener {
            goToLoginPage()
        }

    }

    private fun goToLoginPage() {

        val intent = Intent(this@PrivacyPolicyActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}