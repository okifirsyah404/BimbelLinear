package com.okifirsyah.bimbellinear.presentation.view.attendance_history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.FragmentAttendanceHistoryBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment

class AttendanceHistoryFragment : BaseFragment<FragmentAttendanceHistoryBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAttendanceHistoryBinding {
        return FragmentAttendanceHistoryBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}