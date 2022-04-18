package com.mexcelle.thoughtifydemo.ui.ui.home

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.HomeFragmentBinding
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    lateinit var mActivity: Activity
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: HomeFragmentBinding? = null
    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        binding.setLifecycleOwner(this)
        binding.homeViewModel = homeViewModel


        // Utility.setRegular(activity,txt1)
        return binding.getRoot()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (isAdded && requireActivity() != null) {

            if (activity != null) {
                mActivity = requireActivity()
                Log.e("", "here in is added");
                init()

               Handler().postDelayed({

                    Blurry.with(mActivity)
                        .radius(100)
                        /*.color(Color.argb(220,255, 255, 255))*/
                        .async()
                        .sampling(2).capture(img1).into(img1)

                }, 100)


                Handler().postDelayed({

                    Blurry.with(mActivity)
                        .radius(100)
                        /*.color(Color.argb(220,255, 255, 255))*/
                        .async()
                        .sampling(2).capture(img2).into(img2)

                }, 100)



                Handler().postDelayed({

                    Blurry.with(mActivity)
                        .radius(100)
                        /*.color(Color.argb(220,255, 255, 255))*/
                        .async()
                        .sampling(2).capture(img3).into(img3)

                }, 100)




            }

        } else {

            Log.e("", "here in is not added");

        }
        Log.e("in INIT", "in INIT activity created");


    }




    private fun init() {

        navController = findNavController()

        Utility.setSemibold(mActivity, pp_txt1)
        Utility.setRegular(mActivity, pp_txt2)
        Utility.setSemibold(mActivity, pp_play_now)
        Utility.setSemibold(mActivity, nowthen_txt1)
        Utility.setRegular(mActivity, nowthen_txt2)
        Utility.setSemibold(mActivity, nowthen_play_now)
        Utility.setRegular(mActivity, txt1)
        Utility.setSemibold(mActivity, txt2)

        txt2.text = Constants.USER_NAME



        updateImage()


       // slideUp(main11)

      /*  main11!!.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0F,
            0F,
            main11!!.height.toFloat(),
            0F
        )
        animate.duration = 500
        animate.fillAfter = true
        main11!!.startAnimation(animate)*/

        pp_play_now!!.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToPredict()
            navController.navigate(action)

        }

        nowthen_play_now!!.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToNow()
            navController.navigate(action)

        }


    }

    public fun updateImage() {

        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
            .placeholder(R.drawable.logo1)
            .error(R.drawable.logo1)
            .dontAnimate()

        Glide.with(profile_img.getContext())
            .asBitmap()
            .load(Constants.BASE_URL+"/"+Constants.USER_IMAGE)
            .apply(requestOptions)
            .into(profile_img);

    }


    // slide the view from below itself to the current position
    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
/*
        view.setVisibility(View.VISIBLE);*/
        //view.setAlpha(0.0f);
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            view.height.toFloat(),  // fromYDelta
            0F
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}