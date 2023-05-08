package com.okifirsyah.bimbellinear.presentation.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.okifirsyah.bimbellinear.databinding.FragmentHomeBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.extensions.getGreetings
import java.util.Calendar

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
        binding.homeToolbar.tvGreetings.text = Calendar.getInstance().getGreetings()
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    override fun initIntent() {
        binding.homeToolbar.btnProfile.setOnClickListener {
        }
    }


}