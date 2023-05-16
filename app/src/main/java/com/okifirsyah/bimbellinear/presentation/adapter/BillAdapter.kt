package com.okifirsyah.bimbellinear.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.data.model.BillModel
import com.okifirsyah.bimbellinear.databinding.ItemBillBinding
import com.okifirsyah.bimbellinear.utils.enums.BillStatusEnum
import com.okifirsyah.bimbellinear.utils.extensions.enumValueToTitleCase
import com.okifirsyah.bimbellinear.utils.extensions.toRupiah

class BillAdapter : RecyclerView.Adapter<BillAdapter.BillViewHolder>() {

    private var listBill: ArrayList<BillModel> = ArrayList<BillModel>()

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

        binding.tvBillItemTitle.text = "Tagihan Bimbel Bulan ${item.period}"
        binding.tvBillItemTotal.text = item.total.toRupiah()
        binding.tvBillItemStatus.text = item.status.enumValueToTitleCase()
        binding.tvBillItemDueDate.text = item.date

        if (item.status != BillStatusEnum.BELUM_DIBAYAR.name) {
            binding.tvBillItemStatus.setTextColor(
                bindingContextRes.getColor(
                    getStatusColor(item.status)
                )
            )

            binding.tvBillItemTotal.setTextColor(
                bindingContextRes.getColor(
                    getStatusColor(item.status)
                )
            )
        }

        binding.root.setOnClickListener {
            findNavController(it).navigate(R.id.action_billListFragment_to_billDetailFragment)
        }

    }

    private fun getStatusColor(status: String): Int {
        return when (status) {
            BillStatusEnum.JATUH_TEMPO.name -> R.color.error_50
            BillStatusEnum.SELESAI.name -> R.color.primary_40
            BillStatusEnum.PENDING.name -> R.color.secondary_50
            else -> R.color.neutral_0
        }
    }

}