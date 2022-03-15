package com.mexcelle.everywhendemo.ui

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.util.Utility
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_personal_question.*

class PersonalQuestionsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_question)

        Init()


        location_icon_tv.setOnClickListener {

            val intent = Intent(this@PersonalQuestionsActivity, MapActivity::class.java)
            startActivity(intent)

        }


        continue_txt.setOnClickListener {

            val intent = Intent(this@PersonalQuestionsActivity, HowToUseActivity::class.java)
            startActivity(intent)

        }


    }


    private fun Init() {



        Utility.setSemibold(this@PersonalQuestionsActivity,txt1)
        Utility.setSemibold(this@PersonalQuestionsActivity,continue_txt)
        Utility.setSemibold(this@PersonalQuestionsActivity,txt2)
        Utility.setRegular(this@PersonalQuestionsActivity,location_tv)
        Utility.setRegular(this@PersonalQuestionsActivity,location_tv1)
        Utility.setRegular(this@PersonalQuestionsActivity,ethincity_title_tv)
        Utility.setRegular(this@PersonalQuestionsActivity,age_title_tv)
        Utility.setRegular(this@PersonalQuestionsActivity,gender_title_tv)
        Utility.setRegular(this@PersonalQuestionsActivity,describe_tv)
        Utility.setRegular(this@PersonalQuestionsActivity,age_et)
        Utility.setRegular(this@PersonalQuestionsActivity,describe_ed)
        Utility.setRegular(this@PersonalQuestionsActivity,ancestral_tv)
        Utility.setRegular(this@PersonalQuestionsActivity,ancestral_tv)


        Utility.setSolidFontAwesome(this@PersonalQuestionsActivity,location_icon_tv)
        Utility.setSolidFontAwesome(this@PersonalQuestionsActivity,location_icon)
        Utility.setSolidFontAwesome(this@PersonalQuestionsActivity,ethinicity_icon_tv)
        Utility.setSolidFontAwesome(this@PersonalQuestionsActivity,age_icon)
        Utility.setSolidFontAwesome(this@PersonalQuestionsActivity,gender_icon_tv)
        Utility.setSolidFontAwesome(this@PersonalQuestionsActivity,accenstral_heritage_icon_tv)


        accenstral_heritage_spinner.getBackground().setColorFilter(getResources().getColor(R.color.primary), PorterDuff.Mode.SRC_ATOP);
        ethinicity_spinner.getBackground().setColorFilter(getResources().getColor(R.color.primary), PorterDuff.Mode.SRC_ATOP);
        gender_spinner.getBackground().setColorFilter(getResources().getColor(R.color.primary), PorterDuff.Mode.SRC_ATOP);

    }


}