package com.mexcelle.thoughtifydemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.SettingsFragmentBinding
import com.mexcelle.thoughtifydemo.viewModel.SettingsViewModel

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: SettingsFragmentBinding? =  null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        settingsViewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_settings, container, false
        )
        binding.setLifecycleOwner(this)
        binding.settingsViewModel = settingsViewModel
        return binding.getRoot()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}