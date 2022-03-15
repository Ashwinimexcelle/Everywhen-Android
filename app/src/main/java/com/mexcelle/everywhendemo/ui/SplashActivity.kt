package com.mexcelle.everywhendemo.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.util.Constants
import com.mexcelle.everywhendemo.util.Utility
import com.mexcelle.everywhendemo.util.prefs
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Utility.setRegular(this@SplashActivity,splah_txt1)
        Utility.setRegular(this@SplashActivity,splah_txt2)


        if(prefs?.initAuthToken != null){

            if(prefs?.initAuthToken?.equals("1")!!){

                val handler = Handler()
                handler.postDelayed({
                    val intent = Intent(this@SplashActivity, PreludeActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 3000)

            }else{


                Log.e("prefs.initUsername","prefs.initUsername "+prefs.initUsername);
                Log.e("prefs.initAuthToken","prefs.initAuthToken "+prefs.initAuthToken);
                Log.e("prefs.initEmailid","prefs.initEmailid "+prefs.initEmailid);


                Constants.USER_NAME = prefs.initUsername!!
                Constants.USER_AUTHTOKEN = prefs.initAuthToken!!
                Constants.USER_EMAILID = prefs.initEmailid!!

                val intent = Intent(this@SplashActivity, HomeScreenActivity::class.java)
                startActivity(intent)
            }

        }else{

            val handler = Handler()
            handler.postDelayed({
                val intent = Intent(this@SplashActivity, PreludeActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }





    }
}