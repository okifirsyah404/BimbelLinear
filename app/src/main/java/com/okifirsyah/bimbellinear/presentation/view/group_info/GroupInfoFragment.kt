package com.okifirsyah.bimbellinear.presentation.view.group_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okifirsyah.bimbellinear.data.model.MemberGroupModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.databinding.FragmentGroupInfoBinding
import com.okifirsyah.bimbellinear.presentation.adapter.PersonAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.presentation.dialog.SingleButtonDialog
import com.okifirsyah.bimbellinear.utils.constant.dialogConstant
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.extensions.showCustomDialog
import com.okifirsyah.bimbellinear.utils.extensions.toTitleCase
import org.koin.android.ext.android.inject
import timber.log.Timber

class GroupInfoFragment : BaseFragment<FragmentGroupInfoBinding>() {

    private val viewModel: GroupInfoViewModel by inject()
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

    }

    override fun initProcess() {
        viewModel.fetchGroup()
    }

    override fun initObservers() {
        initGroupInfo()
    }

    override fun initOnRefresh() {
        super.initOnRefresh()
        binding.lRefreshGroupInfo.setOnRefreshListener {
            viewModel.fetchGroup()
            binding.lRefreshGroupInfo.isRefreshing = false
        }
    }

    private fun initGroupInfo() {

        viewModel.groupResult.observe(viewLifecycleOwner) { response ->
            when (response) {

                is ApiResponse.Loading -> {
                    Timber.d("Loading")
                    initLoading(true)
                }

                is ApiResponse.Success -> {
                    initLoading(false)

                    val groupResponse = response.data.data

                    binding.apply {
                        tvGroup.text = groupResponse?.group?.toTitleCase()
                        tvGroupType.text = groupResponse?.type?.toTitleCase()
                        tvGroupMemberCount.text = "${groupResponse?.member?.size} Orang"
                        rvGroup.adapter = personAdapter
                    }

                    personAdapter.setData(
                        groupResponse?.member as ArrayList<MemberGroupModel>
                    )


                }

                is ApiResponse.Error -> {
                    Timber.e(response.errorMessage)
                    showCustomDialog(
                        dialogConstant.ERROR_TITLE,
                        response.errorMessage,
                        dialogType = SingleButtonDialog.FAILED_DIALOG,
                        submitText = "Login",
                        onSubmit = {
//                            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSignInFragment())
                        }
                    )
                }

                else -> {
                    Timber.d("Loading")
                }
            }
        }
    }

    private fun initLoading(isLoading: Boolean) {
        if (isLoading) binding.apply {
            llMainContent.visibility = View.GONE
            homeLoading.layoutLoading.visibility = View.VISIBLE
        } else binding.apply {
            llMainContent.visibility = View.VISIBLE
            homeLoading.layoutLoading.visibility = View.GONE
        }
    }


}