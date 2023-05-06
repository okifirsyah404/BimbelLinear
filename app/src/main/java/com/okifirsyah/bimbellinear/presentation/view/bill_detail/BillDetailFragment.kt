package com.okifirsyah.bimbellinear.presentation.view.bill_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.FragmentBillDetailBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment

class BillDetailFragment : BaseFragment<FragmentBillDetailBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBillDetailBinding {
        return FragmentBillDetailBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}