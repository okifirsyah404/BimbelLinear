package com.okifirsyah.bimbellinear.presentation.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.FragmentHomeBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.extensions.getGreetings
import java.util.Calendar

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initUI() {

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


}