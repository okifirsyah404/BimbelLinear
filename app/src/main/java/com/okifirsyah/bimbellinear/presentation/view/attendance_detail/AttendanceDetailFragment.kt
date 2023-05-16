package com.okifirsyah.bimbellinear.presentation.view.attendance_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.okifirsyah.bimbellinear.databinding.FragmentAttendanceDetailBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant

class AttendanceDetailFragment : BaseFragment<FragmentAttendanceDetailBinding>() {

    private val args: AttendanceDetailFragmentArgs by navArgs()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAttendanceDetailBinding {
        return FragmentAttendanceDetailBinding.inflate(inflater, container, false)
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.title = pageTitleConstant.ATTENDANCE_DETAIL
        binding.toolbar.mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initUI() {
    }

    override fun initProcess() {
        initSchedule()

    }

    override fun initObservers() {
    }

    private fun initSchedule() {

        val schedule = args.scheduleArgs

        if (schedule != null) {
            binding.tvStudy.text = schedule.subject
            binding.tvDateTime.text = "${schedule.day}, ${schedule.time}"
            binding.tvLecturer.text = schedule.teacher
            binding.tvStudyRoom.text = schedule.room
        }

    }


}