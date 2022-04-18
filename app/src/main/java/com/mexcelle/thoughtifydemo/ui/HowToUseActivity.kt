package com.mexcelle.thoughtifydemo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.util.Utility
import kotlinx.android.synthetic.main.activity_how_to_use.*


class HowToUseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to_use)

        Init()

        continue_btn.setOnClickListener {

            val intent = Intent(this@HowToUseActivity, HomeScreenActivity::class.java)
            startActivity(intent)

        }


    }


    private fun Init() {

        Utility.setSemibold(this@HowToUseActivity,txt1)
        Utility.setSemibold(this@HowToUseActivity,txt3)
        Utility.setExodar(this@HowToUseActivity,txt2)
        Utility.setRegular(this@HowToUseActivity,continue_btn)
        Utility.setRegular(this@HowToUseActivity,how_to_use_txt1)
        Utility.setRegular(this@HowToUseActivity,how_to_use_txt2)



    }


}