package com.mexcelle.thoughtifydemo.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.util.Utility
import kotlinx.android.synthetic.main.activity_privacy_policy.*
import kotlinx.android.synthetic.main.activity_terms_and_conditions.*

class PrivacyPolicyActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        Init()

    }

    private fun Init() {

        Utility.setSemibold(this@PrivacyPolicyActivity,privacy_policy_title)
        //Utility.setRegular(this@PrivacyPolicyActivity,privacy_policy_txt)

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


        webview11.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl("http://3.88.247.175/everywhen/privacy_policy")
                return true
            }
        }
        webview11.loadUrl("http://3.88.247.175/everywhen/privacy_policy")
        webview11.setBackgroundColor(Color.TRANSPARENT);

    }

    private fun goToLoginPage() {

        val intent = Intent(this@PrivacyPolicyActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}