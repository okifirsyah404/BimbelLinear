package com.okifirsyah.bimbellinear.presentation.view.attendance_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.constant.PageTitleConstant
import com.okifirsyah.bimbellinear.databinding.FragmentAttendanceDetailBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment

class AttendanceDetailFragment : BaseFragment<FragmentAttendanceDetailBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAttendanceDetailBinding {
        return FragmentAttendanceDetailBinding.inflate(inflater, container, false)
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.title = PageTitleConstant.ATTENDANCE_DETAIL
        binding.toolbar.mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initUI() {
//        TODO("Not yet implemented")
    }

    override fun initProcess() {
//        TODO("Not yet implemented")
    }

    override fun initObservers() {
//        TODO("Not yet implemented")
    }


}