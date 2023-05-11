package com.okifirsyah.bimbellinear.presentation.view.on_boarding.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.FragmentScreenBoarding2Binding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment


class ScreenBoardingFragment2 : BaseFragment<FragmentScreenBoarding2Binding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentScreenBoarding2Binding {
        return FragmentScreenBoarding2Binding.inflate(inflater, container, false)
    }

    override fun initUI() {
        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.btnnext2.setOnClickListener {
            viewPager?.currentItem = 2
        }

        binding.btnskip2.setOnClickListener {
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