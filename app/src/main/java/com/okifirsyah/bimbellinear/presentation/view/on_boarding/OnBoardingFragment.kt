package com.okifirsyah.bimbellinear.presentation.view.on_boarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.FragmentOnBoardingBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOnBoardingBinding {
        return FragmentOnBoardingBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}