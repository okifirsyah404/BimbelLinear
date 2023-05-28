package com.okifirsyah.bimbellinear.presentation.view.about_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.data.model.PersonDevModel
import com.okifirsyah.bimbellinear.databinding.FragmentAboutAppBinding
import com.okifirsyah.bimbellinear.presentation.adapter.DevAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant


class AboutAppFragment : BaseFragment<FragmentAboutAppBinding>() {

    private val devAdapter: DevAdapter by lazy { DevAdapter() }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAboutAppBinding {
        return FragmentAboutAppBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
        binding.rvDevs.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = devAdapter
        }

        devAdapter.setData(personList)
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.apply {
            title = pageTitleConstant.ABOUT_APP
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private val personList = ArrayList<PersonDevModel>(
        listOf(
            PersonDevModel(
                "Oki Firdaus Syah Putra",
                "Android Developer",
                R.drawable.avatar_oki
            ),
            PersonDevModel(
                "Gymnastiar Alma Ghifari",
                "Web Front-End Developer",
                R.drawable.avatar_agim
            ),
            PersonDevModel(
                "Freda Adi Fardana",
                "Web Front-End Developer",
                R.drawable.avatar_freda
            ),
            PersonDevModel(
                "Yoga Novaindra",
                "Web Back-End Developer",
                R.drawable.avatar_yoga
            ),
            PersonDevModel(
                "Siti Rohilah",
                "Quality Assurance",
                R.drawable.avatar_rohil
            ),
        )
    )

}