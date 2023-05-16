package com.okifirsyah.bimbellinear.presentation.view.on_boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.FragmentOnBoardingBinding
import com.okifirsyah.bimbellinear.presentation.adapter.ViewPagerAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.presentation.view.on_boarding.screens.ScreenBoardingFragment1
import com.okifirsyah.bimbellinear.presentation.view.on_boarding.screens.ScreenBoardingFragment2
import com.okifirsyah.bimbellinear.presentation.view.on_boarding.screens.ScreenBoardingFragment3
import org.koin.android.ext.android.inject

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {

    private val viewModel: OnBoardingViewModel by inject()

    private val fragmentList = arrayListOf<Fragment>(
        ScreenBoardingFragment1(),
        ScreenBoardingFragment2(),
        ScreenBoardingFragment3()
    )

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOnBoardingBinding {
        return FragmentOnBoardingBinding.inflate(inflater, container, false)
    }

    override fun initUI() {


        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        binding.dotsIndicator.attachTo(binding.viewPager)


    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    override fun initIntent() {
        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem + 1 < fragmentList.size) {
                binding.viewPager.currentItem += 1
            } else {
                findNavController().navigate(R.id.action_onBoardingFragment_to_signInFragment)
                viewModel.saveFirstLaunch(false)
            }
        }


        binding.btnSkip.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_signInFragment)
            viewModel.saveFirstLaunch(false)
        }
    }

}