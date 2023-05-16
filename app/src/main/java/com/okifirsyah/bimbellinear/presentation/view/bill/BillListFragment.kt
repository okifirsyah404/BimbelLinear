package com.okifirsyah.bimbellinear.presentation.view.bill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okifirsyah.bimbellinear.data.model.BillModel
import com.okifirsyah.bimbellinear.databinding.FragmentBillListBinding
import com.okifirsyah.bimbellinear.presentation.adapter.BillAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.enums.BillStatusEnum

class BillListFragment : BaseFragment<FragmentBillListBinding>() {

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

//        Move to Observer if real data
        val dummyBill = setDummyData()

        binding.rvBill.adapter = billAdapter
        billAdapter.setData(
            dummyBill
        )


    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    private fun setDummyData(): ArrayList<BillModel> {
        val dummyBill = ArrayList<BillModel>()

        dummyBill.add(
            BillModel(
                1,
                period = "Januari 2023",
                date = "12/12/2021",
                status = BillStatusEnum.SELESAI.name,
                total = 100000,
            )
        )

        dummyBill.add(
            BillModel(
                2,
                period = "Februari 2023",
                date = "12/12/2021",
                status = BillStatusEnum.BELUM_DIBAYAR.name,
                total = 100000,
            )
        )

        dummyBill.add(
            BillModel(
                3,
                period = "Maret 2023",
                date = "12/12/2021",
                status = BillStatusEnum.JATUH_TEMPO.name,
                total = 100000,
            )
        )

        dummyBill.add(
            BillModel(
                4,
                period = "April 2023",
                date = "12/12/2021",
                status = BillStatusEnum.PENDING.name,
                total = 100000,
            )
        )

        dummyBill.add(
            BillModel(
                5,
                period = "Mei 2023",
                date = "12/12/2021",
                status = BillStatusEnum.SELESAI.name,
                total = 100000,
            )
        )

        dummyBill.add(
            BillModel(
                6,
                period = "Juni 2023",
                date = "12/12/2021",
                status = BillStatusEnum.BELUM_DIBAYAR.name,
                total = 100000,
            )
        )

        dummyBill.add(
            BillModel(
                7,
                period = "Juli 2023",
                date = "12/12/2021",
                status = BillStatusEnum.JATUH_TEMPO.name,
                total = 100000,
            )
        )

        dummyBill.add(
            BillModel(
                8,
                period = "Agustus 2023",
                date = "12/12/2021",
                status = BillStatusEnum.PENDING.name,
                total = 100000,
            )
        )

        dummyBill.add(
            BillModel(
                9,
                period = "September 2023",
                date = "12/12/2021",
                status = BillStatusEnum.SELESAI.name,
                total = 100000,
            )
        )

        return dummyBill


    }


}