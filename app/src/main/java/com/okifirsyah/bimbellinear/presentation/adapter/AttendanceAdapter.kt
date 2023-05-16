package com.okifirsyah.bimbellinear.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.data.model.AttendanceModel
import com.okifirsyah.bimbellinear.databinding.ItemAttendanceBinding
import com.okifirsyah.bimbellinear.presentation.view.attendance_history.AttendanceHistoryFragmentDirections
import com.okifirsyah.bimbellinear.utils.enums.AttendanceStatusEnum
import com.okifirsyah.bimbellinear.utils.extensions.enumValueToTitleCase

class AttendanceAdapter : RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>() {

    private var itemList: ArrayList<AttendanceModel> = ArrayList()

    fun setData(list: ArrayList<AttendanceModel>) {
        itemList.clear()
        itemList.addAll(list)
    }

    inner class AttendanceViewHolder(private val binding: ItemAttendanceBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val binding =
            ItemAttendanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttendanceViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val binding = ItemAttendanceBinding.bind(holder.itemView)
        val item = itemList[position]

        val bindingContextRes = binding.root.context

        binding.tvAttendanceItemTitle.text = item.subject
        binding.tvAttendanceItemRoom.text = item.room
        binding.tvAttendanceItemLecturer.text = item.teacher
        binding.tvAttendanceDateTime.text = item.date
        binding.tvAttendanceTimeOrStatus.apply {
            text = item.status.enumValueToTitleCase()
            setTextColor(
                when (item.status) {
                    AttendanceStatusEnum.HADIR.name -> bindingContextRes.getColor(
                        R.color.primary_40
                    )

                    AttendanceStatusEnum.TIDAK_HADIR.name -> bindingContextRes.getColor(
                        R.color.error_50
                    )

                    else -> bindingContextRes.getColor(R.color.neutral_0)
                }
            )

        }

        binding.root.setOnClickListener {

            val navDirections =
                AttendanceHistoryFragmentDirections.actionAttendanceHistoryFragmentToAttendanceDetailFragment(
                    attendanceArgs = item
                )

            Navigation.findNavController(it).navigate(navDirections)

        }
    }
}