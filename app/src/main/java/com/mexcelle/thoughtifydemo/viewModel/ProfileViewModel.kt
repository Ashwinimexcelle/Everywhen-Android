package com.mexcelle.thoughtifydemo.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mexcelle.thoughtifydemo.model.*
import com.mexcelle.thoughtifydemo.repository.ProfileFragmentRepository
import com.mexcelle.thoughtifydemo.util.Utility
import org.json.JSONArray

class ProfileViewModel : ViewModel() {

    var updateProfileResponseData: MutableLiveData<LoginResponseData?> =
        MutableLiveData<LoginResponseData?>()

    var ancestralHeritageResponseData: MutableLiveData<ArrayList<AncestralHeritageResponseData?>?> =
        MutableLiveData<ArrayList<AncestralHeritageResponseData?>?>()

    var ethinicityResponseData: MutableLiveData<ArrayList<EthinicityResponseData?>?> =
        MutableLiveData<ArrayList<EthinicityResponseData?>?>()

    var fileUploadResponse: MutableLiveData<FileUploadResponse?>? =
        MutableLiveData<FileUploadResponse?>()

    lateinit var updateProfileInputData: UpdateProfileInputData

    var name: String = ""
    var location: String = ""
    var ethinicity: String = ""
    var age: String = ""
    var gender: String = ""
    var ancestralHeritage: String = ""
    var bio: String = ""


    fun updateProfileData(context: Context): MutableLiveData<LoginResponseData?> {

        /*  updateProfileInputData =
              UpdateProfileInputData(name, location, ethinicity, age, gender, ancestralHeritage, bio)
          Log.e("updateProfileInputData : ", updateProfileInputData.toString())
          updateProfileResponseData = ProfileFragmentRepository.updateProfileData(context, updateProfileInputData)
          return updateProfileResponseData*/



        if (name.length > 0) {

            if (location.length > 0) {


                if (ethinicity.length > 0) {


                    if (ancestralHeritage.length > 0) {

                        if (age.length > 0) {

                            updateProfileInputData =
                                UpdateProfileInputData(
                                    name,
                                    location,
                                    ethinicity,
                                    age,
                                    gender,
                                    ancestralHeritage,
                                    bio
                                )
                            Log.e("updateProfileInputData : ", updateProfileInputData.toString())
                            updateProfileResponseData =
                                ProfileFragmentRepository.updateProfileData(
                                    context,
                                    updateProfileInputData
                                )


                        } else {

                            Utility.hideProgressDialog(context)
                            Utility.showDialog(
                                context,
                                "Error !!",
                                "Please enter valid Age.",
                                "Ok",
                                "Cancel"
                            )

                        }


                    } else {

                        Utility.hideProgressDialog(context)
                        Utility.showDialog(
                            context,
                            "Error !!",
                            "Please select valid Ancestral Heritage.",
                            "Ok",
                            "Cancel"
                        )

                    }


                } else {

                    Utility.hideProgressDialog(context)
                    Utility.showDialog(
                        context,
                        "Error !!",
                        "Please select valid Ethnicity.",
                        "Ok",
                        "Cancel"
                    )

                }

            } else {

                Utility.hideProgressDialog(context)
                Utility.showDialog(
                    context,
                    "Error !!",
                    "Please enter valid location.",
                    "Ok",
                    "Cancel"
                )

            }
        } else {

            Utility.hideProgressDialog(context)

            Utility.showDialog(context, "Error !!", "Please enter valid name.", "Ok", "Cancel")

        }
        return updateProfileResponseData


    }


    fun getEthinicityList(context: Context): MutableLiveData<ArrayList<EthinicityResponseData?>?> {

        ethinicityResponseData = ProfileFragmentRepository.getEthinicityList(context)
        return ethinicityResponseData

    }

    fun getAncestralHeritageList(context: Context): MutableLiveData<ArrayList<AncestralHeritageResponseData?>?>? {

        ancestralHeritageResponseData = ProfileFragmentRepository.getAncestralHeritage(context)
        return ancestralHeritageResponseData

    }


    fun uploadImage(
        context: Context,
        userProfileUploadImageRequestModel: UserProfileUploadImageRequestModel
    ): MutableLiveData<FileUploadResponse?>? {


        fileUploadResponse =
            ProfileFragmentRepository.uploadImage(context, userProfileUploadImageRequestModel)
        return fileUploadResponse

    }


}