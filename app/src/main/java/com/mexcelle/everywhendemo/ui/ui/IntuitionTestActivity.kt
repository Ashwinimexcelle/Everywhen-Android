package com.mexcelle.everywhendemo.ui.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.ui.HowToUseActivity
import com.mexcelle.everywhendemo.ui.IntoductionActivity
import com.mexcelle.everywhendemo.ui.PersonalQuestionsActivity
import com.mexcelle.everywhendemo.util.Utility
import kotlinx.android.synthetic.main.activity_intuition_test.*
import kotlinx.android.synthetic.main.activity_otp.*

class IntuitionTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intuition_test)

        Init()


      /*  continue_btn.setOnClickListener {

            val intent = Intent(this@IntuitionTestActivity, HowToUseActivity::class.java)
            startActivity(intent)

        }*/


        continue_btn.setOnClickListener {

            val intent = Intent(this@IntuitionTestActivity, PersonalQuestionsActivity::class.java)
            startActivity(intent)

        }

    }

    private fun Init() {

        Utility.setRegular(this@IntuitionTestActivity,continue_btn)
        Utility.setSemibold(this@IntuitionTestActivity,txt1)
        Utility.setRegular(this@IntuitionTestActivity,txt2)
        Utility.setSemibold(this@IntuitionTestActivity,txt11)


    }


}
