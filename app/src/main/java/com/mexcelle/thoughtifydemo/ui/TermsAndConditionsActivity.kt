package com.mexcelle.thoughtifydemo.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.util.Utility
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.activity_terms_and_conditions.*

class TermsAndConditionsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_conditions)
        Init1()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    private fun Init1() {

        Utility.setSemibold(this@TermsAndConditionsActivity,terms_and_conditions_title)
       /* Utility.setRegular(this@TermsAndConditionsActivity,terms_txt)*/
        Utility.setRegular(this@TermsAndConditionsActivity,accept_cb)
        Utility.setRegular(this@TermsAndConditionsActivity,terms_continue_txt2)




        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl("http://3.88.247.175/everywhen/terms_conditions")
                return true
            }
        }
        webview.loadUrl("http://3.88.247.175/everywhen/terms_conditions")
        webview.setBackgroundColor(Color.TRANSPARENT);



        terms_continue_txt2!!.setOnClickListener {


            if ( terms_continue_txt2.getBackground().getConstantState()==getResources().
                getDrawable(R.drawable.rounded_corner_blue_bg).getConstantState()){

                goToPrivacyPolicy()
            }else{


            }


            /*if(terms_continue_txt2.background)
            goToPrivacyPolicy()*/
        }


        accept_cb.setOnClickListener(View.OnClickListener {

            if(accept_cb.isChecked){

                terms_continue_txt2.setBackgroundResource(R.drawable.rounded_corner_blue_bg)
                terms_continue_txt2.setTextColor(getColor(R.color.white))

            }else{

                terms_continue_txt2.setBackgroundResource(R.drawable.rounded_corner_add_friend)
                terms_continue_txt2.setTextColor(getColor(R.color.grey_txt_color1))

            }

        })


        if(accept_cb.isChecked){

            terms_continue_txt2.setBackgroundResource(R.drawable.rounded_corner_blue_bg)
            terms_continue_txt2.setTextColor(getColor(R.color.white))
        }else{

            terms_continue_txt2.setBackgroundResource(R.drawable.rounded_corner_add_friend)
            terms_continue_txt2.setTextColor(getColor(R.color.grey_txt_color1))
        }
    }


    private fun goToPrivacyPolicy() {

        val intent = Intent(this@TermsAndConditionsActivity, PrivacyPolicyActivity::class.java)
        startActivity(intent)
    }
}