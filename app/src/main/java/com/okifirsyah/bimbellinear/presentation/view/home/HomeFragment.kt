package com.okifirsyah.bimbellinear.presentation.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.data.model.ScheduleModel
import com.okifirsyah.bimbellinear.databinding.FragmentHomeBinding
import com.okifirsyah.bimbellinear.presentation.adapter.ScheduleAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.extensions.getGreetings
import java.util.Calendar

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val scheduleAdapter: ScheduleAdapter by lazy { ScheduleAdapter() }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initUI() {

        binding.rvSchedules.layoutManager = LinearLayoutManager(context)

//        Move to Observer if real data
        val dummySchedule = setDummyData()

        binding.rvSchedules.adapter = scheduleAdapter
        scheduleAdapter.setData(
            dummySchedule
        )

    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    override fun initIntent() {
        binding.homeToolbar.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

    override fun initAppBar() {
        binding.homeToolbar.tvGreetings.text = Calendar.getInstance().getGreetings()
    }

    override fun initOnRefresh() {
        binding.root.setOnRefreshListener {
            Toast.makeText(context, "Refreshed", Toast.LENGTH_SHORT).show()
            binding.root.isRefreshing = false
        }
    }


    private fun initNotifyChangePassword() {

        binding.lNotifyPassword.tvNotifyPasswordDesc.text =
            "Password anda telah diubah, silahkan login kembali"

        binding.lNotifyPassword.ivCloseNotifyPassword.setOnClickListener {
            binding.lNotifyPassword.llNotifyPassword.apply {
                animation =
                    AnimationSet(false).apply {
                        addAnimation(
                            AlphaAnimation(
                                1.0f,
                                0.0f
                            ).apply {
                                duration = 400
                                interpolator = AccelerateInterpolator()
                            }
                        )
                    }

                visibility = View.GONE
            }
        }

        binding.lNotifyPassword.llNotifyPassword.apply {
            animation = AnimationSet(false).apply {
                addAnimation(
                    AlphaAnimation(
                        0.0f,
                        1.0f
                    ).apply {
                        duration = 400
                        interpolator = DecelerateInterpolator()
                    }
                )
            }
            visibility = View.VISIBLE

        }

    }

    private fun setDummyData(): ArrayList<ScheduleModel> {
        val item = ArrayList<ScheduleModel>()
        item.add(ScheduleModel(1, "Matematika", "A1", "John Doe", "Senin", "10.00"))
        item.add(ScheduleModel(2, "Matematika", "A2", "John Doe", "Selasa", "10.00"))
        item.add(ScheduleModel(3, "Matematika", "A3", "John Doe", "Rabu", "10.00"))
        item.add(ScheduleModel(4, "Matematika", "A4", "John Doe", "Kamis", "10.00"))
        item.add(ScheduleModel(5, "Matematika", "A5", "John Doe", "Jumat", "10.00"))
        item.add(ScheduleModel(6, "Matematika", "A6", "John Doe", "Sabtu", "10.00"))
        item.add(ScheduleModel(7, "Matematika", "A7", "John Doe", "Minggu", "10.00"))

        return item
    }


}