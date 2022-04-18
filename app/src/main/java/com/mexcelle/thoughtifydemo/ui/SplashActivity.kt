package com.mexcelle.thoughtifydemo.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import com.mexcelle.thoughtifydemo.util.prefs
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Utility.setRegular(this@SplashActivity,splah_txt1)
        Utility.setRegular(this@SplashActivity,splah_txt2)
        Utility.setExodar(this@SplashActivity,logo_name)
        Utility.setRegular(this@SplashActivity,logo_name2)

        logo_name.alpha = 0.0F
        logo_name.animate()
            .translationY(-10f)
            .alpha(1.0f)
            .setDuration(3000)

            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    logo_name.setVisibility(View.VISIBLE)
                }
            })


        logo_name2.alpha = 0.0F
        logo_name2.animate()
            .translationY(-30f)
            .alpha(1.0f)
            .setDuration(3000)

            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    logo_name2.setVisibility(View.VISIBLE)
                }
            })




        val scaleDownX = ObjectAnimator.ofFloat(logo, "scaleX", 1.5f)
        val scaleDownY = ObjectAnimator.ofFloat(logo, "scaleY", 1.5f)
        scaleDownX.duration = 5000
        scaleDownY.duration = 5000

        val scaleDown = AnimatorSet()
        scaleDown.play(scaleDownX).with(scaleDownY)
        scaleDown.start()



        logo.alpha = 0.0F
        logo.animate()
            .translationY(20f)
            .alpha(1.0f)
            .setDuration(5000)

            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    logo.setVisibility(View.VISIBLE)


                    if(prefs?.initAuthToken != null){

                        if(prefs?.initAuthToken?.equals("1")!!){

                            val handler = Handler()
                            handler.postDelayed({
                                val intent = Intent(this@SplashActivity, PreludeActivity::class.java)
                                startActivity(intent)
                                finish()
                            }, 10000)

                        }else{


                            Log.e("prefs.initUsername","prefs.initUsername "+prefs.initUsername);
                            Log.e("prefs.initAuthToken","prefs.initAuthToken "+prefs.initAuthToken);
                            Log.e("prefs.initEmailid","prefs.initEmailid "+prefs.initEmailid);
                            Log.e("prefs.initEmailid","prefs.initEmailid "+prefs.isUserDataAdded);



                            Constants.USER_NAME = prefs.initUsername!!
                            Constants.USER_AUTHTOKEN = prefs.initAuthToken!!
                            Constants.USER_EMAILID = prefs.initEmailid!!
                            Constants.USER_IMAGE = prefs.initUserImageUrl!!
                            Constants.USER_DATA = prefs.isUserDataAdded!!
                            Constants.USER_ID = prefs.initUserId!!


                            if(Constants.USER_DATA){

                                val intent = Intent(this@SplashActivity, HomeScreenActivity::class.java)
                                startActivity(intent)

                            }else{

                                val intent = Intent(this@SplashActivity, PersonalQuestionsActivity::class.java)
                                startActivity(intent)
                            }

                        }

                    }else{

                        val handler = Handler()
                        handler.postDelayed({
                            val intent = Intent(this@SplashActivity, PreludeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }, 10000)
                    }

                }
            })

    }
}