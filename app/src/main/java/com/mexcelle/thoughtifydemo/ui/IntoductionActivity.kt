package com.mexcelle.thoughtifydemo.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.ui.ui.IntuitionTestActivity
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.activity_introduction.*
import kotlinx.android.synthetic.main.fragment_home.txt1
import kotlinx.android.synthetic.main.fragment_home.txt2


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

        txt2.text = Constants.USER_NAME
        val animation: Animation = AlphaAnimation(1.0F, 0.0F) //to change visibility from visible to invisible

        animation.duration = 1000 //1 second duration for each animation cycle
        animation.interpolator = LinearInterpolator()
        animation.repeatCount = Animation.INFINITE //repeating indefinitely
        animation.repeatMode = Animation.REVERSE //animation will start from end point once ended.
        animation_view1.setApplyingOpacityToLayersEnabled(true)
        animation_view1.alpha = 0.5F

        Handler().postDelayed({

            Blurry.with(this@IntoductionActivity)
                .radius(100)
                /*.color(Color.argb(220,255, 255, 255))*/
                .async()
                .sampling(2).capture(rl).into(rl)

        }, 100)
    }

}