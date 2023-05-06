package com.okifirsyah.bimbellinear.presentation.view.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
        binding.toolbar.mainToolbar.title = "Splash"


    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}