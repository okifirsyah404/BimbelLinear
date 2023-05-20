package com.okifirsyah.bimbellinear.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.data.model.BillModel
import com.okifirsyah.bimbellinear.databinding.ItemBillBinding
import com.okifirsyah.bimbellinear.presentation.view.bill.BillListFragmentDirections
import com.okifirsyah.bimbellinear.utils.enums.BillStatusEnum
import com.okifirsyah.bimbellinear.utils.extensions.enumValueToTitleCase
import com.okifirsyah.bimbellinear.utils.extensions.getFormattedDate
import com.okifirsyah.bimbellinear.utils.extensions.titleCaseToEnumValue
import com.okifirsyah.bimbellinear.utils.extensions.toDate
import com.okifirsyah.bimbellinear.utils.extensions.toRupiah
import com.okifirsyah.bimbellinear.utils.extensions.toTitleCase

class BillAdapter : RecyclerView.Adapter<BillAdapter.BillViewHolder>() {

    private var listBill: ArrayList<BillModel> = ArrayList()

    fun setData(items: ArrayList<BillModel>) {
        listBill.clear()
        listBill.addAll(items)
    }

    inner class BillViewHolder(private val binding: ItemBillBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        val binding = ItemBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BillViewHolder(binding)
    }

    override fun getItemCount(): Int = listBill.size

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        val binding = ItemBillBinding.bind(holder.itemView)
        val item = listBill[position]

        val bindingContextRes = binding.root.context

        binding.tvBillItemTitle.text =
            "Tagihan Bimbel Bulan ${item.month?.toTitleCase()} ${item.year}"
        binding.tvBillItemTotal.text = item.amount?.toRupiah()
        binding.tvBillItemStatus.text = item.status?.enumValueToTitleCase()
        binding.tvBillItemDueDate.text = item.dueDate?.toDate()?.getFormattedDate()

        if (item.status?.toTitleCase()
                ?.titleCaseToEnumValue() != BillStatusEnum.BELUM_BAYAR.name
        ) {
            binding.apply {
                tvBillItemStatus.setTextColor(
                    bindingContextRes.getColor(
                        getBillStatusColor(item.status ?: BillStatusEnum.BELUM_BAYAR.name)
                    )
                )
                tvBillItemTotal.setTextColor(
                    bindingContextRes.getColor(
                        getBillStatusColor(
                            item.status?.toTitleCase()?.titleCaseToEnumValue()
                                ?: BillStatusEnum.BELUM_BAYAR.name
                        )
                    )
                )
            }
        }

        binding.root.setOnClickListener { view ->

            val navDirection =
                BillListFragmentDirections.actionBillListFragmentToBillDetailFragment(
                    item
                )

            findNavController(view).navigate(
                navDirection
            )
        }

    }

    private fun getBillStatusColor(status: String): Int {
        return when (status) {
            BillStatusEnum.JATUH_TEMPO.name -> R.color.error_50
            BillStatusEnum.LUNAS.name -> R.color.primary_40
            BillStatusEnum.PENDING.name -> R.color.secondary_50
            else -> R.color.neutral_0
        }
    }

}