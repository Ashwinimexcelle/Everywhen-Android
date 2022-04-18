package com.mexcelle.thoughtifydemo.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.transition.*
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.util.Utility
import kotlinx.android.synthetic.main.activity_prelude.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_splash.*

class PreludeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prelude)

        proceed_btn.setOnClickListener {

            val intent = Intent(this@PreludeActivity, OnBoardingActivity::class.java)
            startActivity(intent)
        }
        //setupWindowAnimations()

        setupAnimation();

        Utility.setbold(this@PreludeActivity,proceed_btn)
        Utility.setRegular(this@PreludeActivity,prelude_txt1)
        Utility.setRegular(this@PreludeActivity,prelude_txt2)
        Utility.setRegular(this@PreludeActivity,prelude_txt4)
        Utility.setRegular(this@PreludeActivity,prelude_txt3)

    }

    private fun setupAnimation() {


        prelude_txt1.alpha = 0.0F
        prelude_txt4.alpha = 0.0F
        prelude_txt3.alpha = 0.0F
        proceed_btn.alpha = 0.0F
        prelude_txt2.alpha = 0.0F


        prelude_txt1.animate()

            .translationYBy(40f)
            .alpha(1.0f)
            .setDuration(2000)

            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    prelude_txt1.setVisibility(View.VISIBLE)

                    prelude_txt4.animate()
                        .translationYBy(40f)

                        .alpha(1.0f)
                        .setDuration(2000)

                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                prelude_txt4.setVisibility(View.VISIBLE)

                                prelude_txt3.animate()
                                    .translationYBy(40f)
                                    .alpha(1.0f)
                                    .setDuration(2000)

                                    .setListener(object : AnimatorListenerAdapter() {
                                        override fun onAnimationEnd(animation: Animator?) {
                                            prelude_txt3.setVisibility(View.VISIBLE)


                                            prelude_txt2.animate()
                                                .translationYBy(40f)

                                                .alpha(1.0f)
                                                .setDuration(2000)

                                                .setListener(object : AnimatorListenerAdapter() {
                                                    override fun onAnimationEnd(animation: Animator?) {
                                                        prelude_txt2.setVisibility(View.VISIBLE)

                                                        prelude_txt2.animate()
                                                            .translationYBy(40f)
                                                            .alpha(1.0f)
                                                            .setDuration(2000)

                                                            .setListener(object : AnimatorListenerAdapter() {
                                                                override fun onAnimationEnd(animation: Animator?) {
                                                                    prelude_txt3.setVisibility(View.VISIBLE)


                                                                    val handler = Handler()
                                                                    handler.postDelayed(
                                                                        Runnable {

                                                                            proceed_btn.animate()
                                                                                .translationX(-10f)
                                                                                .translationXBy(50f)

                                                                                .alpha(1.0f)
                                                                                .setDuration(2000)

                                                                                .setListener(object : AnimatorListenerAdapter() {
                                                                                    override fun onAnimationEnd(animation: Animator?) {
                                                                                        proceed_btn.setVisibility(View.VISIBLE)
                                                                                    }
                                                                                })
                                                                        },
                                                                        4000
                                                                    )
                                                                }
                                                            })



                                                    }
                                                })



                                        }
                                    })



                            }
                        })



                }
            })











    }


    private fun setupWindowAnimations() {
        val transition: Transition?
       // transition = if (type == TYPE_PROGRAMMATICALLY) {
        transition = buildEnterTransition()
        /*} else {
            TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom)
        }*/
        window.enterTransition = transition
    }
    private fun buildEnterTransition(): Visibility? {
        val enterTransition = Slide()
        enterTransition.duration = resources.getInteger(R.integer.anim_duration_long).toLong()
        enterTransition.slideEdge = Gravity.LEFT
        return enterTransition
    }


    private fun buildEnterTransition1(): Visibility? {
        val enterTransition = Fade()
        enterTransition.duration = resources.getInteger(R.integer.anim_duration_long).toLong()
        // This view will not be affected by enter transition animation
        enterTransition.excludeTarget(R.id.logo, true)
        return enterTransition
    }

    private fun buildReturnTransition(): Visibility? {
        val enterTransition: Visibility = Slide()
        enterTransition.duration = resources.getInteger(R.integer.anim_duration_long).toLong()
        return enterTransition
    }
}