package com.mexcelle.thoughtifydemo.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.PersonalQuestionsActivityBinding
import com.mexcelle.thoughtifydemo.model.AncestralHeritageResponseData
import com.mexcelle.thoughtifydemo.model.EthinicityResponseData
import com.mexcelle.thoughtifydemo.model.UpdateProfileInputData
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import com.mexcelle.thoughtifydemo.util.prefs
import com.mexcelle.thoughtifydemo.viewModel.PersonalQuestionViewModel
import kotlinx.android.synthetic.main.activity_personal_question.*
import kotlinx.android.synthetic.main.activity_personal_question.accenstral_heritage_icon_tv
import kotlinx.android.synthetic.main.activity_personal_question.accenstral_heritage_spinner
import kotlinx.android.synthetic.main.activity_personal_question.accenstral_heritage_spinnerId
import kotlinx.android.synthetic.main.activity_personal_question.age_et
import kotlinx.android.synthetic.main.activity_personal_question.age_icon
import kotlinx.android.synthetic.main.activity_personal_question.age_title_tv
import kotlinx.android.synthetic.main.activity_personal_question.ancestral_tv
import kotlinx.android.synthetic.main.activity_personal_question.continue_txt
import kotlinx.android.synthetic.main.activity_personal_question.describe_ed
import kotlinx.android.synthetic.main.activity_personal_question.describe_tv
import kotlinx.android.synthetic.main.activity_personal_question.ethincity_title_tv
import kotlinx.android.synthetic.main.activity_personal_question.ethinicity_icon_tv
import kotlinx.android.synthetic.main.activity_personal_question.ethinicity_spinner
import kotlinx.android.synthetic.main.activity_personal_question.ethinicity_spinnerId
import kotlinx.android.synthetic.main.activity_personal_question.gender_icon_tv
import kotlinx.android.synthetic.main.activity_personal_question.gender_spinner
import kotlinx.android.synthetic.main.activity_personal_question.gender_spinnerId
import kotlinx.android.synthetic.main.activity_personal_question.gender_title_tv
import kotlinx.android.synthetic.main.activity_personal_question.location_icon
import kotlinx.android.synthetic.main.activity_personal_question.location_icon_tv
import kotlinx.android.synthetic.main.activity_personal_question.location_tv
import kotlinx.android.synthetic.main.activity_personal_question.location_tv1
import kotlinx.android.synthetic.main.fragment_profile.*

class PersonalQuestionsActivity: AppCompatActivity() {


    private lateinit var binding: PersonalQuestionsActivityBinding
    private lateinit var personalQuestionViewModel: PersonalQuestionViewModel
    var ethinicityList: ArrayList<EthinicityResponseData?>? = ArrayList()
    var ancestralHeritageList: ArrayList<AncestralHeritageResponseData?>? = ArrayList()
    val ethinicityWithoutIdList: ArrayList<String> = ArrayList()
    val ancestralHeritageWithoutIdList: ArrayList<String> = ArrayList()
    var genderList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_personal_question)


        personalQuestionViewModel = ViewModelProvider(this).get(PersonalQuestionViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_personal_question)
        binding.setLifecycleOwner(this)
        binding.personalQuestionViewModel = personalQuestionViewModel


        Init()


        location_icon_tv.setOnClickListener {

            val intent = Intent(this@PersonalQuestionsActivity, MapActivity::class.java)
            startActivity(intent)

        }


        continue_txt.setOnClickListener {



            updateProfileDetails()






            /*  val intent = Intent(this@PersonalQuestionsActivity, HowToUseActivity::class.java)
              startActivity(intent)*/

        }


    }

    private fun updateProfileDetails() {

        if (Utility.isOnline(this)) {

            personalQuestionViewModel.updateProfileData(this)!!
                .observe(this, Observer { updateProfileResponseData ->

                    Utility.hideProgressDialog(this)
                    prefs.isUserDataAdded = true

                    Log.e(
                        "updateProfileResponseData ",
                        "updateProfileResponseData " + updateProfileResponseData
                    );


                    val intent = Intent(this@PersonalQuestionsActivity, HowToUseActivity::class.java)
                    startActivity(intent)

                })

        } else {

            Utility.showDialog(
                this,
                "Network Error !!",
                "Please check your network connection.",
                "Ok",
                "Cancel"
            )

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


        genderList.add("Male")
        genderList.add("Female")
        genderList.add("Others")

        val adap1: ArrayAdapter<*> =
            ArrayAdapter(this, R.layout.spinner_item, genderList)
        adap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        gender_spinner!!.setAdapter(adap1)

        gender_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                gender_spinnerId.text = genderList[position]
                Log.e("gender_spinnerId.text","gender_spinnerId.text "+gender_spinnerId.text);
            }
        }




        getEthinicityMaster()

    }

    private fun getEthinicityMaster() {




        if (Utility.isOnline(this)) {

            Utility.showProgressDialog(this)
            personalQuestionViewModel.getEthinicityList(this)!!
                .observe(this, Observer { ethinicityResponseData ->
                    // Utility.showProgressDialog(mActivity)
                    Utility.hideProgressDialog(this)

                    Log.e(
                        "ethinicityResponseData ",
                        "ethinicityResponseData " + ethinicityResponseData
                    );
                    ethinicityList = ethinicityResponseData
                    if (ethinicityResponseData?.size!! > 0) {

                        for (i in 0 until ethinicityResponseData?.size!!) {

                            ethinicityWithoutIdList.add(ethinicityResponseData[i]!!.value)
                        }


                        val adap1: ArrayAdapter<*> =
                            ArrayAdapter(this, R.layout.spinner_item, ethinicityWithoutIdList)
                        adap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        ethinicity_spinner!!.setAdapter(adap1)


                        ethinicity_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {

                            }

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                /*profileViewModel.age = profileViewModel.ageList[position]*/
                                //age_id.setText(profileViewModel.ageList[position])
                                ethinicity_spinnerId.text = ethinicityList!![position]!!.id
                            }
                        }

                    }


                    getAncestralHeritageMaster()


                })

        } else {

            Utility.showDialog(
                this,
                "Network Error !!",
                "Please check your network connection.",
                "Ok",
                "Cancel"
            )

        }




    }

    private fun getAncestralHeritageMaster() {

        if (Utility.isOnline(this)) {

            Utility.showProgressDialog(this)
            personalQuestionViewModel.getAncestralHeritageList(this)!!
                .observe(this, Observer { ancestralHeritageResponseData ->
                    Utility.hideProgressDialog(this)
                    Log.e(
                        "ancestralHeritageResponseData ",
                        "ancestralHeritageResponseData " + ancestralHeritageResponseData
                    );

                    if (ancestralHeritageResponseData?.size!! > 0) {

                        ancestralHeritageList = ancestralHeritageResponseData
                        for (i in 0 until ancestralHeritageResponseData?.size!!) {

                            ancestralHeritageWithoutIdList.add(ancestralHeritageResponseData[i]!!.value)

                        }

                        val adap1: ArrayAdapter<*> =
                            ArrayAdapter(
                                this,
                                R.layout.spinner_item,
                                ancestralHeritageWithoutIdList
                            )
                        adap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        accenstral_heritage_spinner!!.setAdapter(adap1)

                        accenstral_heritage_spinner?.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }

                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    accenstral_heritage_spinnerId.text = ancestralHeritageList!![position]!!.id

                                }
                            }

                    }

                })

        } else {

            Utility.showDialog(
                this,
                "Network Error !!",
                "Please check your network connection.",
                "Ok",
                "Cancel"
            )

        }




    }


}