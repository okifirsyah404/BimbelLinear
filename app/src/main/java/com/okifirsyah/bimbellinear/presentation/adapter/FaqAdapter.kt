package com.okifirsyah.bimbellinear.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.data.model.FaqModel
import com.okifirsyah.bimbellinear.databinding.ItemFaqBinding

class FaqAdapter : RecyclerView.Adapter<FaqAdapter.ViewHolder>() {

    private var faqItemList: ArrayList<FaqModel> = ArrayList()

    fun setData(list: ArrayList<FaqModel>) {
        faqItemList.clear()
        faqItemList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFaqBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = faqItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val faq = faqItemList[position]
        holder.bind(faq)
    }

    inner class ViewHolder(private val binding: ItemFaqBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(faq: FaqModel) {

            val isExpanded = faq.isExpanded

            binding.apply {
                tvTitleFaq.text = faq.title
                tvQuestionFaq.text = faq.question
                tvAnswerFaq.text = faq.answer

                if (isExpanded) {
                    llContentFaq.visibility = android.view.View.VISIBLE
                    ivArrowFaq.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
                } else {
                    llContentFaq.visibility = android.view.View.GONE
                }

                llTileFaq.setOnClickListener {
                    faq.isExpanded = !faq.isExpanded
                    notifyItemChanged(absoluteAdapterPosition)
                }
            }
        }
    }

}