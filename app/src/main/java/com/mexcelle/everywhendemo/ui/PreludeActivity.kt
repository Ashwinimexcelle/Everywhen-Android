package com.mexcelle.everywhendemo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.util.Utility
import kotlinx.android.synthetic.main.activity_prelude.*
import kotlinx.android.synthetic.main.activity_register.*

class PreludeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prelude)



        proceed_btn.setOnClickListener {

            val intent = Intent(this@PreludeActivity, OnBoardingActivity::class.java)
            startActivity(intent)


        }

        Utility.setbold(this@PreludeActivity,proceed_btn)
        Utility.setRegular(this@PreludeActivity,prelude_txt1)
        Utility.setRegular(this@PreludeActivity,prelude_txt2)




    }
}