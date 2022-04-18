package com.mexcelle.thoughtifydemo.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class OnBoardingFragment : Fragment() {

    companion object {
        private const val ARG_POSITION = "ARG_POSITION"

        fun getInstance(position: Int) = OnBoardingFragment().apply {
            arguments = bundleOf(ARG_POSITION to position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }



}