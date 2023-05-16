package com.okifirsyah.bimbellinear.presentation.view.on_boarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.okifirsyah.bimbellinear.databinding.FragmentScreenBoarding3Binding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment


class ScreenBoardingFragment3 : BaseFragment<FragmentScreenBoarding3Binding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentScreenBoarding3Binding {
        return FragmentScreenBoarding3Binding.inflate(inflater, container, false)
    }

    override fun initUI() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    private fun onBoardingFinished() {
//        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
//        val editor = sharedPref.edit()
//        editor.putBoolean("Finished", true)
//        editor.apply()
    }
}