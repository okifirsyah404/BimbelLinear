package com.okifirsyah.bimbellinear.presentation.view.bill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okifirsyah.bimbellinear.data.model.BillModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.databinding.FragmentBillListBinding
import com.okifirsyah.bimbellinear.presentation.adapter.BillAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.presentation.dialog.SingleButtonDialog
import com.okifirsyah.bimbellinear.presentation.view.home.HomeFragmentDirections
import com.okifirsyah.bimbellinear.utils.constant.dialogConstant
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.extensions.showCustomDialog
import org.koin.android.ext.android.inject
import timber.log.Timber

class BillListFragment : BaseFragment<FragmentBillListBinding>() {

    private val viewModel: BillListViewModel by inject()
    private val billAdapter: BillAdapter by lazy { BillAdapter() }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBillListBinding {
        return FragmentBillListBinding.inflate(inflater, container, false)
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.title = pageTitleConstant.BILL_LIST
        binding.toolbar.mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initUI() {
        binding.rvBill.layoutManager = LinearLayoutManager(context)
    }

    override fun initProcess() {
        viewModel.fetchBill()
    }

    override fun initObservers() {
        initBillList()
    }

    private fun initBillList() {
        viewModel.billResult.observe(viewLifecycleOwner) { response ->
            when (response) {

                is ApiResponse.Loading -> {
                    Timber.tag("BILL").d("Loading")
                    initLoading(true)
                }

                is ApiResponse.Success -> {
                    initLoading(false)

                    val responseData = response.data.data

                    if (responseData?.size!! > 0) {
                        binding.rvBill.adapter = billAdapter

                        billAdapter.setData(
                            responseData as ArrayList<BillModel>
                        )
                    } else {
                        binding.layoutEmptyItem.apply {
                            emptyView.visibility = View.VISIBLE
                            tvEmptyMessage.text = "Belum ada tagihan"
                        }

                    }

                }

                is ApiResponse.Error -> {
                    Timber.tag("BILL").e(response.errorMessage)
                    showCustomDialog(
                        dialogConstant.ERROR_TITLE,
                        response.errorMessage,
                        dialogType = SingleButtonDialog.FAILED_DIALOG,
                        submitText = "Login",
                        onSubmit = {
                            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSignInFragment())
                        }
                    )
                }

                else -> {
                    Timber.tag("BILL").d("Loading 2")
                }
            }
        }
    }

    private fun initLoading(isLoading: Boolean) {
        if (isLoading) binding.apply {
            rvBill.visibility = View.GONE
            billLoading.layoutLoading.visibility = View.VISIBLE
        } else binding.apply {
            rvBill.visibility = View.VISIBLE
            billLoading.layoutLoading.visibility = View.GONE
        }
    }

}