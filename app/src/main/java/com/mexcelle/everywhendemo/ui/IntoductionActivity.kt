package com.mexcelle.everywhendemo.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.ui.ui.IntuitionTestActivity
import com.mexcelle.everywhendemo.util.Utility
import kotlinx.android.synthetic.main.activity_introduction.*
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.txt1
import kotlinx.android.synthetic.main.fragment_home.txt2
import android.view.animation.AlphaAnimation

class IntoductionActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)

        Init()

        proceed_btn.setOnClickListener {

            val intent = Intent(this@IntoductionActivity, IntuitionTestActivity::class.java)
            startActivity(intent)

        }


    }


    private fun Init() {



        Utility.setRegular(this@IntoductionActivity,txt1)
        Utility.setSemibold(this@IntoductionActivity,txt2)
        Utility.setRegular(this@IntoductionActivity,txt3)
        Utility.setExodar(this@IntoductionActivity,txt4)
        Utility.setSemibold(this@IntoductionActivity,txt5)
        Utility.setRegular(this@IntoductionActivity,proceed_btn)
        Utility.setRegular(this@IntoductionActivity,intro_txt1)


        val animation: Animation = AlphaAnimation(1.0F, 0.0F) //to change visibility from visible to invisible

        animation.duration = 1000 //1 second duration for each animation cycle
        animation.interpolator = LinearInterpolator()
        animation.repeatCount = Animation.INFINITE //repeating indefinitely
        animation.repeatMode = Animation.REVERSE //animation will start from end point once ended.

        user_imageView.startAnimation(animation) //to start animation
        user_imageView1.startAnimation(animation) //to start animation
        user_imageView2.startAnimation(animation) //to start animation
        user_imageView3.startAnimation(animation) //to start animation
        user_imageView4.startAnimation(animation) //to start animation
        user_imageView5.startAnimation(animation) //to start animation




    }



}