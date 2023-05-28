package com.okifirsyah.bimbellinear.presentation.view.attendance_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okifirsyah.bimbellinear.data.model.AttendanceModel
import com.okifirsyah.bimbellinear.databinding.FragmentAttendanceHistoryBinding
import com.okifirsyah.bimbellinear.presentation.adapter.AttendanceAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.enums.AttendanceStatusEnum

class AttendanceHistoryFragment : BaseFragment<FragmentAttendanceHistoryBinding>() {

    private val attendanceAdapter: AttendanceAdapter by lazy { AttendanceAdapter() }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAttendanceHistoryBinding {
        return FragmentAttendanceHistoryBinding.inflate(inflater, container, false)
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.title = pageTitleConstant.ATTENDANCE_LIST
        binding.toolbar.mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initUI() {
        binding.rvAttendance.layoutManager = LinearLayoutManager(context)

//        Move to Observer if real data
        binding.rvAttendance.adapter = attendanceAdapter

        attendanceAdapter.setData(
            setDummyData()
        )
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    private fun setDummyData(): ArrayList<AttendanceModel> {
        val attendance = ArrayList<AttendanceModel>()
        attendance.add(
            AttendanceModel(
                1,
                "John Doe",
                "A1",
                "John Doe",
                "Senin",
                AttendanceStatusEnum.TIDAK_HADIR.name
            )
        )
        attendance.add(
            AttendanceModel(
                2,
                "John Doe",
                "A2",
                "John Doe",
                "Selasa",
                AttendanceStatusEnum.HADIR.name
            )
        )
        attendance.add(
            AttendanceModel(
                3,
                "John Doe",
                "A3",
                "John Doe",
                "Rabu",
                AttendanceStatusEnum.HADIR.name
            )
        )
        attendance.add(
            AttendanceModel(
                4,
                "John Doe",
                "A4",
                "John Doe",
                "Kamis",
                AttendanceStatusEnum.HADIR.name
            )
        )
        attendance.add(
            AttendanceModel(
                5,
                "John Doe",
                "A5",
                "John Doe",
                "Jumat",
                AttendanceStatusEnum.TIDAK_HADIR.name
            )
        )
        attendance.add(
            AttendanceModel(
                6,
                "John Doe",
                "A6",
                "John Doe",
                "Sabtu",
                AttendanceStatusEnum.TIDAK_HADIR.name
            )
        )
        attendance.add(
            AttendanceModel(
                7,
                "John Doe",
                "A7",
                "John Doe",
                "Minggu",
                AttendanceStatusEnum.HADIR.name
            )
        )
        attendance.add(
            AttendanceModel(
                8,
                "John Doe",
                "A8",
                "John Doe",
                "Senin",
                AttendanceStatusEnum.HADIR.name
            )
        )
        attendance.add(
            AttendanceModel(
                9,
                "John Doe",
                "A9",
                "John Doe",
                "Selasa",
                AttendanceStatusEnum.HADIR.name
            )
        )
        attendance.add(
            AttendanceModel(
                10,
                "John Doe",
                "A10",
                "John Doe",
                "Rabu",
                AttendanceStatusEnum.HADIR.name
            )
        )

        return attendance
    }

}