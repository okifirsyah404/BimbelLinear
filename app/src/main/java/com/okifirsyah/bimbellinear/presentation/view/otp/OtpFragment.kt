package com.okifirsyah.bimbellinear.presentation.view.otp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.FragmentOtpBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment

class OtpFragment : BaseFragment<FragmentOtpBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOtpBinding {
        return FragmentOtpBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
        TODO("Not yet implemented")
    }

    override fun initProcess() {
        TODO("Not yet implemented")
    }

    override fun initObservers() {
        TODO("Not yet implemented")
    }

}