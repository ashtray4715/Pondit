package com.ashtray.pondit.features.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ashtray.pondit.common.ui.GPFragment
import com.ashtray.pondit.databinding.FragmentSplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment: GPFragment() {

    private val viewModel : SplashScreenViewModel by viewModels {
        SplashViewModelFactory()
    }

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override val uniqueTag = "SplashScreenFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

}