package com.okifirsyah.bimbellinear.presentation.view.group_info

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.FragmentGroupInfoBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment

class GroupInfoFragment : BaseFragment<FragmentGroupInfoBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentGroupInfoBinding {
        return FragmentGroupInfoBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
        TODO("Not yet implemented")
    }

    override fun initProcess() {
        TODO("Not yet implemented")
    }

    override fun initObservers() {
        TODO("Not yet implemented")
    }

}