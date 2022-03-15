package com.mexcelle.everywhendemo.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.databinding.FriendsFragmentBinding
import com.mexcelle.everywhendemo.databinding.PurchaseFlairFragmentBinding
import com.mexcelle.everywhendemo.util.Utility
import com.mexcelle.everywhendemo.viewModel.FriendsViewModel
import com.mexcelle.everywhendemo.viewModel.PurchaseFlairViewModel
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.fragment_purchase_flair.*

class PurchaseFlairFragment : Fragment() {

    private lateinit var purchaseFlairViewModel: PurchaseFlairViewModel
    private var _binding: PurchaseFlairFragmentBinding? =  null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var mActivity: Activity
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        purchaseFlairViewModel = ViewModelProvider(this).get(PurchaseFlairViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_purchase_flair, container, false
        )
        binding.setLifecycleOwner(this)
        binding.purchaseFlairViewModel = purchaseFlairViewModel
        return binding.getRoot()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(isAdded && requireActivity() != null){

            if(activity != null){
                mActivity = requireActivity()
                Log.e("","here in is added");
                init()

            }

        }else{

            Log.e("","here in is not added");

        }
        Log.e("in INIT","in INIT activity created");


    }

    private fun init() {

        val window = mActivity?.window
        navController = findNavController()

        Utility.setRegular(mActivity,txt11)
        Utility.setRegular(mActivity,txt33)
        Utility.setSemibold(mActivity,name11)
        Utility.setSemibold(mActivity,name1)
        Utility.setSemibold(mActivity,name2)
        Utility.setSemibold(mActivity,name3)
        Utility.setSemibold(mActivity,name4)
        Utility.setSemibold(mActivity,score)

        Utility.setSemibold(mActivity,score1)
        Utility.setSemibold(mActivity,score2)
        Utility.setSemibold(mActivity,score3)
        Utility.setSemibold(mActivity,score4)
        Utility.setRegular(mActivity,txt43)
        Utility.setSemibold(mActivity,txt44)



        one.setOnClickListener(View.OnClickListener {

            val action = PurchaseFlairFragmentDirections.actionPurchaseflairToFriendfragment()
            navController.navigate(action)
        })


    }


}