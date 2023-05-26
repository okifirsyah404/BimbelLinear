package com.okifirsyah.bimbellinear.presentation.view.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.okifirsyah.bimbellinear.databinding.FragmentFaqBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment

class FaqFragment : BaseFragment<FragmentFaqBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFaqBinding {
        return FragmentFaqBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}