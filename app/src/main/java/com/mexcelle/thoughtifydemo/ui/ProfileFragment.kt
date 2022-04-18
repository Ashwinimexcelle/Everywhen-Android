package com.mexcelle.thoughtifydemo.ui

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.ProfileFragmentBinding
import com.mexcelle.thoughtifydemo.model.AncestralHeritageResponseData
import com.mexcelle.thoughtifydemo.model.EthinicityResponseData
import com.mexcelle.thoughtifydemo.model.LoginResponseData
import com.mexcelle.thoughtifydemo.model.UserProfileUploadImageRequestModel
import com.mexcelle.thoughtifydemo.ui.ui.home.HomeFragment
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import com.mexcelle.thoughtifydemo.viewModel.ProfileViewModel
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.fragment_predict_the_picture_ques.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.accenstral_heritage_icon_tv
import kotlinx.android.synthetic.main.fragment_profile.accenstral_heritage_spinner
import kotlinx.android.synthetic.main.fragment_profile.age_et
import kotlinx.android.synthetic.main.fragment_profile.age_icon
import kotlinx.android.synthetic.main.fragment_profile.age_title_tv
import kotlinx.android.synthetic.main.fragment_profile.ancestral_tv
import kotlinx.android.synthetic.main.fragment_profile.continue_txt
import kotlinx.android.synthetic.main.fragment_profile.describe_ed
import kotlinx.android.synthetic.main.fragment_profile.describe_tv
import kotlinx.android.synthetic.main.fragment_profile.ethincity_title_tv
import kotlinx.android.synthetic.main.fragment_profile.ethinicity_icon_tv
import kotlinx.android.synthetic.main.fragment_profile.ethinicity_spinner
import kotlinx.android.synthetic.main.fragment_profile.gender_spinner
import kotlinx.android.synthetic.main.fragment_profile.gender_icon_tv
import kotlinx.android.synthetic.main.fragment_profile.gender_title_tv
import kotlinx.android.synthetic.main.fragment_profile.location_icon
import kotlinx.android.synthetic.main.fragment_profile.location_icon_tv
import kotlinx.android.synthetic.main.fragment_profile.location_tv
import kotlinx.android.synthetic.main.fragment_profile.location_tv1
import kotlinx.android.synthetic.main.fragment_profile_screen.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: ProfileFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var mActivity: Activity
    var ethinicityList: ArrayList<EthinicityResponseData?>? = ArrayList()
    var ancestralHeritageList: ArrayList<AncestralHeritageResponseData?>? = ArrayList()
    val ethinicityWithoutIdList: ArrayList<String> = ArrayList()
    val ancestralHeritageWithoutIdList: ArrayList<String> = ArrayList()
    lateinit var getProfileResponseData1: LoginResponseData
    var genderList: ArrayList<String> = ArrayList()
    private val OPERATION_CAPTURE_PHOTO = 1
    private val OPERATION_CHOOSE_PHOTO = 2
    private var finalFile: File? = null
    private lateinit var navController: NavController
    var isEthnicityApiCalled: Boolean = false
    var isAncestralHeritageApiCalled: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile, container, false
        )
        binding.setLifecycleOwner(this)
        binding.profileViewModel = profileViewModel
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (isAdded && requireActivity() != null) {

            if (activity != null) {
                mActivity = requireActivity()
                Log.e("", "here in is added");
                init()

            }

        } else {

            Log.e("", "here in is not added");

        }
        Log.e("in INIT", "in INIT activity created");

    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.READ_EXTERNAL_STORAGE, false) -> {
                    // Precise location access granted.

                }
                permissions.getOrDefault(Manifest.permission.WRITE_EXTERNAL_STORAGE, false) -> {
                    // Only approximate location access granted.


                }
                permissions.getOrDefault(Manifest.permission.CAMERA, false) -> {
                    // Only approximate location access granted.


                }
                else -> {
                    // No location access granted.

                }
            }
        }


        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA

            )
        )

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA

                ),
                100
            );

        }

    }


    private fun init() {

        navController = findNavController()
        genderList.add("Male")
        genderList.add("Female")
        genderList.add("Others")

        val adap1: ArrayAdapter<*> =
            ArrayAdapter(mActivity, R.layout.spinner_item, genderList)
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
                Log.e("gender_spinnerId.text", "gender_spinnerId.text " + gender_spinnerId.text);
            }
        }


        if (Constants.getProfileResponseData1?.gender != null) {

            if (Constants.getProfileResponseData1?.gender.equals("Male")) {

                gender_spinner.setSelection(0)
            } else if (Constants.getProfileResponseData1?.gender.equals("Female")) {

                gender_spinner.setSelection(1)
            } else if (Constants.getProfileResponseData1?.gender.equals("Other")) {

                gender_spinner.setSelection(2)
            }
        }

        setTextFont()
        if (ethinicity_spinner != null) {

            getEthinicityMaster()

        }

        location_icon_tv.setOnClickListener {

            val intent = Intent(mActivity, MapActivity::class.java)
            startActivityForResult(intent, 1)

        }

        user_image11.setOnClickListener {

            chooseImageDialog("test", "test", mActivity)
        }

        continue_txt!!.setOnClickListener {

            if (finalFile != null) {
                if (finalFile!!.exists()) {

                    var compressedImageFile = Compressor(mActivity).compressToFile(finalFile)

                    val reqFile: RequestBody =
                        RequestBody.create("image/*".toMediaTypeOrNull(), compressedImageFile)
                    val body: MultipartBody.Part =
                        MultipartBody.Part.createFormData(
                            "image",
                            compressedImageFile.getName(),
                            reqFile
                        )
                    val userProfileUploadImageRequestModel =
                        UserProfileUploadImageRequestModel(body)
                    Utility.showProgressDialog(mActivity)
                    uploadImage(userProfileUploadImageRequestModel)

                } else {

                    Utility.showProgressDialog(mActivity)
                    updateProfile();
                }

            } else {

                Utility.showProgressDialog(mActivity)
                updateProfile();
            }
        }
    }


    private fun uploadImage(userProfileUploadImageRequestModel: UserProfileUploadImageRequestModel) {

        if (Utility.isOnline(mActivity)) {

            profileViewModel.uploadImage(mActivity, userProfileUploadImageRequestModel)!!
                .observe(viewLifecycleOwner, Observer { profileUpdateResponseData ->


                    Log.e(
                        "profileUpdateResponseData",
                        "profileUpdateResponseData " + profileUpdateResponseData
                    );

                    if (profileUpdateResponseData?.avatar?.length!! > 0) {

                        updateProfile()

                    }


                })

        } else {

            Utility.hideProgressDialog(mActivity)

            Utility.showDialog(
                mActivity,
                "Network Error !!",
                "Please check your network connection.",
                "Ok",
                "Cancel"
            )
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data);

        Log.e("resultCode", "resultCode " + resultCode);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 101) {


                val selectedImage1: Uri = data?.getData()!!

                var myBitmap: Bitmap? = null
                try {
                    myBitmap = MediaStore.Images.Media.getBitmap(
                        mActivity.getContentResolver(),
                        selectedImage1
                    )
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                user_image11.setImageBitmap(myBitmap)
                finalFile = File(getRealPathFromURI(selectedImage1))
                Log.e("data", "data " + data);
                Log.e("finalFile", "finalFile " + finalFile);


            } else if (requestCode == 102) {

                //val selectedImage1: Uri = data?.getData()!!
                var myBitmap: Bitmap? = null
                myBitmap = data?.getExtras()?.get("data") as Bitmap?
                user_image11.setImageBitmap(myBitmap)
                finalFile = File(getRealPathFromURI(getImageUri(mActivity, myBitmap!!)!!))
                Log.e("data", "data " + data);
                Log.e("finalFile", "finalFile " + finalFile);
            }
        }
    }

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    fun getRealPathFromURI(uri: Uri): String? {
        val cursor: Cursor? =
            mActivity.getContentResolver().query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val idx = cursor?.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        return idx?.let { cursor?.getString(it) }
    }


    fun chooseImageDialog(
        title: String?, message: String?,
        context: Context?
    ) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Gallery",
            DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI
                )
                startActivityForResult(intent, 101)
            })
        alertDialog.setNegativeButton("Camera",
            DialogInterface.OnClickListener { dialog, which ->
                val intentfile = Intent(
                    "android.media.action.IMAGE_CAPTURE"
                )
                startActivityForResult(intentfile, 102)
                dialog.dismiss()
            })
        alertDialog.show()
    }

    private fun openGallery() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, OPERATION_CHOOSE_PHOTO)
    }

    private fun updateProfile() {

        if (Utility.isOnline(mActivity)) {

            //Utility.showProgressDialog(mActivity)
            profileViewModel.updateProfileData(mActivity)!!
                .observe(requireActivity(), Observer { updateProfileResponseData ->

                    Utility.hideProgressDialog(mActivity)
                    //ProfileScreenFragment().updateData(updateProfileResponseData)

                    Log.e(
                        "updateProfileResponseData ",
                        "updateProfileResponseData " + updateProfileResponseData
                    );
                    Constants.getProfileResponseData1 = updateProfileResponseData
                    Constants.USER_IMAGE = updateProfileResponseData!!.avatar
                    /*HomeScreenActivity().updateImage()
                    HomeFragment().updateImage()*/


                    Log.e(
                        "Constants.getProfileResponseData1 ",
                        "Constants.getProfileResponseData1 " + Constants.getProfileResponseData1
                    );


                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(mActivity)
                    alertDialog.setTitle("Update Profile")
                    alertDialog.setMessage("Profile Updated Successfully !!")
                    alertDialog.setPositiveButton("OK",
                        DialogInterface.OnClickListener { dialog, which ->

                            //navController.popBackStack()
                            val action =
                                ProfileFragmentDirections.actionProfilefragmentToProfilescreenfragment(
                                    "profile"
                                )
                            navController.navigate(action)

                        })

                    alertDialog.show()


                })

        } else {

            Utility.showDialog(
                mActivity,
                "Network Error !!",
                "Please check your network connection.",
                "Ok",
                "Cancel"
            )

        }


    }


    private fun getEthinicityMaster() {

        if (Utility.isOnline(mActivity)) {

            Utility.showProgressDialog(mActivity)
            profileViewModel.getEthinicityList(mActivity)!!
                .observe(requireActivity(), Observer { ethinicityResponseData ->
                    // Utility.showProgressDialog(mActivity)
                    Utility.hideProgressDialog(mActivity)

/*
                    if(!isEthnicityApiCalled){
*/

                    Log.e(
                        "ethinicityResponseData ",
                        "ethinicityResponseData " + ethinicityResponseData
                    );
                    ethinicityList = ethinicityResponseData
                    if (ethinicityResponseData?.size!! > 0) {

                        for (i in 0 until ethinicityResponseData?.size!!) {

                            ethinicityWithoutIdList.add(ethinicityResponseData[i]!!.value)
                        }

                        if (ethinicity_spinner != null) {

                            val adap1: ArrayAdapter<*> =
                                ArrayAdapter(
                                    mActivity,
                                    R.layout.spinner_item,
                                    ethinicityWithoutIdList
                                )
                            adap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            ethinicity_spinner!!.setAdapter(adap1)


                            ethinicity_spinner?.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
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

                    }

                    if (ethinicity_spinnerId != null) {

                        for (i in 0 until ethinicityResponseData?.size!!) {

                            if (Constants.getProfileResponseData1?.ancestralHeritage?.value.equals(
                                    ethinicityResponseData[i]!!.value
                                )
                            ) {

                                ethinicity_spinner.setSelection(i)
                                break
                            }

                        }
                    }

                    if (accenstral_heritage_spinner != null) {

                        getAncestralHeritageMaster()

                    }



                    isEthnicityApiCalled = true

                    /*}*/

                })

        } else {

            Utility.showDialog(
                mActivity,
                "Network Error !!",
                "Please check your network connection.",
                "Ok",
                "Cancel"
            )

        }


    }


    private fun getAncestralHeritageMaster() {

        if (Utility.isOnline(mActivity)) {

            Utility.showProgressDialog(mActivity)
            profileViewModel.getAncestralHeritageList(mActivity)!!
                .observe(requireActivity(), Observer { ancestralHeritageResponseData ->
                    Utility.hideProgressDialog(mActivity)

/*
                    if(!isAncestralHeritageApiCalled){
*/


                    Log.e(
                        "ancestralHeritageResponseData ",
                        "ancestralHeritageResponseData " + ancestralHeritageResponseData
                    );

                    if (ancestralHeritageResponseData?.size!! > 0) {

                        ancestralHeritageList = ancestralHeritageResponseData
                        for (i in 0 until ancestralHeritageResponseData?.size!!) {

                            ancestralHeritageWithoutIdList.add(ancestralHeritageResponseData[i]!!.value)

                        }


                        if(accenstral_heritage_spinner != null){


                            val adap1: ArrayAdapter<*> =
                                ArrayAdapter(
                                    mActivity,
                                    R.layout.spinner_item,
                                    ancestralHeritageWithoutIdList
                                )
                            adap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            accenstral_heritage_spinner!!.setAdapter(adap1)
                            for (i in 0 until ancestralHeritageResponseData?.size!!) {

                                if (Constants.getProfileResponseData1?.ancestralHeritage?.value.equals(
                                        ancestralHeritageResponseData[i]!!.value
                                    )
                                ) {

                                    accenstral_heritage_spinner.setSelection(i)
                                    break
                                }

                            }




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
                                        accenstral_heritage_spinnerId.text =
                                            ancestralHeritageList!![position]!!.id

                                    }
                                }

                            updateUI()

                        }

                    }

                    isAncestralHeritageApiCalled = true
                    /*  }*/


                })

        } else {

            Utility.showDialog(
                mActivity,
                "Network Error !!",
                "Please check your network connection.",
                "Ok",
                "Cancel"
            )

        }

    }

    private fun updateUI() {

        if(name_ed != null){


            name_ed.setText(Constants.getProfileResponseData1?.name)
            location_tv1.setText(Constants.getProfileResponseData1?.location)
            age_et.setText(Constants.getProfileResponseData1?.age)
            describe_ed.setText(Constants.getProfileResponseData1?.bio)

            val requestOptions =
                RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                    .placeholder(R.drawable.logo1).error(R.drawable.logo1)
                    .dontAnimate()

            if (Constants.getProfileResponseData1?.avatar != null) {

                Glide.with(user_image11.getContext())
                    .asBitmap()
                    .load(Constants.BASE_URL + "/" + Constants.getProfileResponseData1?.avatar)
                    .apply(requestOptions)
                    .into(user_image11);
            }



        }

    }


    private fun setTextFont() {

        Utility.setSemibold(mActivity, continue_txt)
        Utility.setRegular(mActivity, location_tv)
        Utility.setRegular(mActivity, location_tv1)
        Utility.setRegular(mActivity, ethincity_title_tv)
        Utility.setRegular(mActivity, age_title_tv)
        Utility.setRegular(mActivity, gender_title_tv)
        Utility.setRegular(mActivity, describe_tv)
        Utility.setRegular(mActivity, age_et)
        Utility.setRegular(mActivity, describe_ed)
        Utility.setRegular(mActivity, ancestral_tv)
        Utility.setRegular(mActivity, ancestral_tv)
        Utility.setRegular(mActivity, ed_t1)
        Utility.setSemibold(mActivity, ed_t2)

        Utility.setRegular(mActivity, notification_id)
        Utility.setRegular(mActivity, location_switch_id)
        Utility.setSemibold(mActivity, invite_friends_txt)
        Utility.setRegular(mActivity, name_tv)
        Utility.setRegular(mActivity, name_ed)
        Utility.setSolidFontAwesome(mActivity, location_icon_tv)
        Utility.setSolidFontAwesome(mActivity, location_icon)
        Utility.setSolidFontAwesome(mActivity, ethinicity_icon_tv)
        Utility.setSolidFontAwesome(mActivity, age_icon)
        Utility.setSolidFontAwesome(mActivity, gender_icon_tv)
        Utility.setSolidFontAwesome(mActivity, accenstral_heritage_icon_tv)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

