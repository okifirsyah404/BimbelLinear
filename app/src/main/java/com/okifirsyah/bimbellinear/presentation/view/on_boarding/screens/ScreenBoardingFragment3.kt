package com.okifirsyah.bimbellinear.presentation.view.on_boarding.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.okifirsyah.bimbellinear.R
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
        binding.btnfinish.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_signInFragment)
            onBoardingFinished()
        }

        binding.btnskip3.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_signInFragment)
            onBoardingFinished()
        }
    }

    override fun initProcess() {
        TODO("Not yet implemented")
    }

    override fun initObservers() {
        TODO("Not yet implemented")
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}