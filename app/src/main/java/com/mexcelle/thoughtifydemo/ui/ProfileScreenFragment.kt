package com.mexcelle.thoughtifydemo.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.ProfileScreenFragmentBinding
import com.mexcelle.thoughtifydemo.model.LoginResponseData
import com.mexcelle.thoughtifydemo.ui.ui.home.HomeFragment
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import com.mexcelle.thoughtifydemo.viewModel.ProfileScreenViewModel
import kotlinx.android.synthetic.main.fragment_profile_screen.*


class ProfileScreenFragment : Fragment(), Observer<LoginResponseData?> {

    private lateinit var profileScreenViewModel: ProfileScreenViewModel
    private var _binding: ProfileScreenFragmentBinding? = null
    lateinit var mActivity: Activity
    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var isGetProfileApiCalled: Boolean = false
    lateinit var x: MutableLiveData<LoginResponseData?>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // isGetProfileApiCalled = false

        profileScreenViewModel = ViewModelProvider(this).get(ProfileScreenViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile_screen, container, false
        )
        binding.setLifecycleOwner(this)
        binding.profileScreenViewModel = profileScreenViewModel
        Log.e("", "isGetProfileApiCalled " + isGetProfileApiCalled)
        Log.e("", "isGetProfileApiCalled " + isGetProfileApiCalled)
        Log.e("Here in cretaed ", "Here in cretaed  ");
        //init()
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (isAdded && requireActivity() != null) {

            if (activity != null) {
                mActivity = requireActivity()
                Log.e("", "here in is added");
                // isGetProfileApiCalled = false
                x = profileScreenViewModel.getUserProfileDetails(mActivity)

                init()
                Log.e("", "isGetProfileApiCalled " + isGetProfileApiCalled)

            }

        } else {

            Log.e("", "here in is not added");

        }
        Log.e("in INIT", "in INIT activity created");

        Log.e("", "isGetProfileApiCalled " + isGetProfileApiCalled)

    }

    public fun updateData(getProfileResponseDatax: LoginResponseData?) {
        profileScreenViewModel.consumeResponse(getProfileResponseDatax)
    }

    private fun init() {


        navController = findNavController()
        val window = mActivity?.window
        Log.e("from", "from " + arguments?.get("from"))
/*
        if(arguments?.get("from") != null){
            Log.e("from","from "+arguments?.get("from"))

            *//*if(arguments?.get("from")?.equals("profile")!!){*//*

                Log.e("from","from "+arguments?.get("from"))

                Utility.hideProgressDialog(mActivity)
                //isGetProfileApiCalled = true
                Log.e("Constants.getProfileResponseData1","Constants.getProfileResponseData1 "+Constants.getProfileResponseData1?.age)
                //profileScreenViewModel.consumeResponse(Constants.getProfileResponseData1)
               // profileScreenViewModel.getProfileResponseData.removeObserver()
                updateUI(Constants.getProfileResponseData1)
              //  isGetProfileApiCalled = false

          *//* }else{

                isGetProfileApiCalled = false

            }*//*
        }*/

        if (arguments?.get("from") != null) {
            if (arguments?.get("from")?.equals("profile")!!) {


            } else {


            }
        }
        setTextFont()


        edit_tv!!.setOnClickListener {

            Log.e("here in observer", "here in observer @@@" + x.hasActiveObservers());
            Log.e("here in observer", "here in observer @@@" + x.hasObservers());

            val action =
                ProfileScreenFragmentDirections.actionProfilescreenfragmentToProfilefragment()
            navController.navigate(action)

        }

    }

    private fun setTextFont() {

        Log.e("mActivity", "mActivity " + mActivity)


        Utility.setSolidFontAwesome(mActivity!!, edit_tv)
        Utility.setSemibold(mActivity!!, profile_name)
        Utility.setSemibold(mActivity!!, add_friend)
        Utility.setSemibold(mActivity!!, awards_tv)
        Utility.setRegular(mActivity!!, location_title_txt)
        Utility.setRegular(mActivity!!, location_txt)
        Utility.setRegular(mActivity!!, ethinicity_title_tv)
        Utility.setRegular(mActivity!!, ethinicity_tv)
        Utility.setRegular(mActivity!!, age_title_tv)
        Utility.setRegular(mActivity!!, age_tv)
        Utility.setSemibold(mActivity!!, txt55)
        Utility.setSemibold(mActivity!!, txt45)

        Utility.setSemibold(mActivity!!, leader_board_title_tv)
        Utility.setRegular(mActivity!!, leader_board_analysis_title_tv)
        Utility.setRegular(mActivity!!, leader_board_analysis_title_tv)
        Utility.setRegular(mActivity!!, leader_board_analysis_title_tv)
        Utility.setRegular(mActivity!!, leaderboard_score_tv)
        Utility.setRegular(mActivity!!, leaderboard_analysis_tv)

        Utility.setRegular(mActivity!!, monthly_score_title_tv)
        Utility.setRegular(mActivity!!, weekly_score_title_tv)
        Utility.setRegular(mActivity!!, daily_score_title_tv)
        Utility.setRegular(mActivity!!, monthly_score_per_tv)
        Utility.setRegular(mActivity!!, weekly_score_per_tv)
        Utility.setRegular(mActivity!!, daily_score_per_tv)

        Utility.setbold(mActivity!!, monthly_score_tv)
        Utility.setbold(mActivity!!, weekly_score_tv)
        Utility.setbold(mActivity!!, daily_score_tv)

        getUserDetails()

    }

    private fun getUserDetails() {

        if (Utility.isOnline(mActivity)) {


            if (!isGetProfileApiCalled) {
                Utility.showProgressDialog(mActivity)



                x.observe(requireActivity(), Observer { loginResponseData ->


                    Log.e("", "isGetProfileApiCalled " + isGetProfileApiCalled)

                    Utility.hideProgressDialog(mActivity)
                    Log.e(
                        "loginResponseData !! ",
                        "loginResponseData !!" + loginResponseData
                    );

                    Constants.getProfileResponseData1 = loginResponseData!!
                    Constants.USER_IMAGE = loginResponseData.avatar
                   // HomeScreenActivity().updateImage()
                    //HomeFragment().updateImage()
                    updateUI(loginResponseData)
                    //isGetProfileApiCalled = true
                    x.removeObservers(this);


                })
            }

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

    private fun updateUI(loginResponseData: LoginResponseData?) {

        Log.e("loginResponseData", "loginResponseData " + loginResponseData);
        Log.e("mActivity", "mActivity " + mActivity);

        if (profile_name != null) {
            profile_name.text = loginResponseData?.username
            location_txt.text = loginResponseData?.location
            age_tv.text = loginResponseData?.age
            ethinicity_tv.text = loginResponseData?.ethnicity?.value
            leaderboard_score_tv.text = loginResponseData?.stats?.points

            val requestOptions =
                RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                    .dontAnimate()

            if (loginResponseData?.avatar != null) {

                Glide.with(user_image.getContext())
                    .asBitmap()
                    .load(Constants.BASE_URL + "/" + loginResponseData?.avatar)
                    .apply(requestOptions)
                    .into(user_image);
            }

        }

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        profileScreenViewModel.getUserProfileDetails(mActivity).removeObservers(this)
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        profileScreenViewModel.getUserProfileDetails(mActivity).removeObservers(this)
        _binding = null

    }

    override fun onStop() {
        profileScreenViewModel.getUserProfileDetails(mActivity).removeObservers(this)
        super.onStop()
    }

    override fun onChanged(t: LoginResponseData?) {

        x.removeObservers(this)
    }

}
