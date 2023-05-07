package com.okifirsyah.bimbellinear.presentation.view.bill

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.constant.PageTitleConstant
import com.okifirsyah.bimbellinear.databinding.FragmentBillListBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment

class BillListFragment : BaseFragment<FragmentBillListBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBillListBinding {
        return FragmentBillListBinding.inflate(inflater, container, false)
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.title = PageTitleConstant.BILL_LIST
        binding.toolbar.mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initUI() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }


}