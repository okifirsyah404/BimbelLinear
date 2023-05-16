package com.okifirsyah.bimbellinear.presentation.view.group_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okifirsyah.bimbellinear.data.model.PersonModel
import com.okifirsyah.bimbellinear.databinding.FragmentGroupInfoBinding
import com.okifirsyah.bimbellinear.presentation.adapter.PersonAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant

class GroupInfoFragment : BaseFragment<FragmentGroupInfoBinding>() {

    private val personAdapter: PersonAdapter by lazy { PersonAdapter() }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentGroupInfoBinding {
        return FragmentGroupInfoBinding.inflate(inflater, container, false)
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.title = pageTitleConstant.GROUP_INFO
        binding.toolbar.mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initUI() {
        binding.rvGroup.layoutManager = LinearLayoutManager(context)

        val dummyPerson = setDummyData()

        binding.rvGroup.adapter = personAdapter
        personAdapter.setData(
            dummyPerson
        )
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    private fun setDummyData(): ArrayList<PersonModel> {
        val person = ArrayList<PersonModel>()
        person.add(PersonModel(1, "John Doe", "Ketua"))
        person.add(PersonModel(2, "John Doe", "Anggota"))
        person.add(PersonModel(3, "John Doe", "Anggota"))
        person.add(PersonModel(4, "John Doe", "Anggota"))
        person.add(PersonModel(5, "John Doe", "Anggota"))
        person.add(PersonModel(6, "John Doe", "Anggota"))
        person.add(PersonModel(7, "John Doe", "Anggota"))
        person.add(PersonModel(8, "John Doe", "Anggota"))

        return person
    }

}