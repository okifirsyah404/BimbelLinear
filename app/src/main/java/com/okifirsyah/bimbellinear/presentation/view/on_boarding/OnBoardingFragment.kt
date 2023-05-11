package com.okifirsyah.bimbellinear.presentation.view.on_boarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.FragmentOnBoardingBinding
import com.okifirsyah.bimbellinear.presentation.adapter.ViewPagerAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.presentation.view.on_boarding.screens.ScreenBoardingFragment1
import com.okifirsyah.bimbellinear.presentation.view.on_boarding.screens.ScreenBoardingFragment2
import com.okifirsyah.bimbellinear.presentation.view.on_boarding.screens.ScreenBoardingFragment3

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOnBoardingBinding {
        return FragmentOnBoardingBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
        val fragmentList = arrayListOf<Fragment>(
            ScreenBoardingFragment1(),
            ScreenBoardingFragment2(),
            ScreenBoardingFragment3()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

//        val view = layoutInflater.inflate(binding?.viewPager, )
        binding.viewPager.adapter = adapter
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}