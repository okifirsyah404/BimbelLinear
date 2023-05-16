package com.okifirsyah.bimbellinear.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.okifirsyah.bimbellinear.data.model.ScheduleModel
import com.okifirsyah.bimbellinear.databinding.ItemAttendanceBinding
import com.okifirsyah.bimbellinear.presentation.view.home.HomeFragmentDirections

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    private var itemList: ArrayList<ScheduleModel> = ArrayList()
//    private var rvAttached: RecyclerView? = null

    fun setData(list: ArrayList<ScheduleModel>) {
        itemList.clear()
        itemList.addAll(list)
    }

    inner class ScheduleViewHolder(private val binding: ItemAttendanceBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding =
            ItemAttendanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val binding = ItemAttendanceBinding.bind(holder.itemView)
        val item = itemList[position]

        binding.tvAttendanceItemTitle.text = item.subject
        binding.tvAttendanceItemRoom.text = item.room
        binding.tvAttendanceItemLecturer.text = item.teacher
        binding.tvAttendanceDateTime.text = item.day
        binding.tvAttendanceTimeOrStatus.text = item.time

        binding.root.setOnClickListener {

            val navDirections =
                HomeFragmentDirections.actionHomeFragmentToAttendanceDetailFragment(scheduleArgs = item)

            findNavController(it).navigate(navDirections)

        }

    }

}