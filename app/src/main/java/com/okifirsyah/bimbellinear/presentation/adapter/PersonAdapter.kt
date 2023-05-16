package com.okifirsyah.bimbellinear.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.okifirsyah.bimbellinear.data.model.PersonModel
import com.okifirsyah.bimbellinear.databinding.ItemGroupInfoBinding

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private var personList: ArrayList<PersonModel> = ArrayList()

    fun setData(list: ArrayList<PersonModel>) {
        personList.clear()
        personList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.ViewHolder {
        val binding =
            ItemGroupInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonAdapter.ViewHolder, position: Int) {
        val binding = ItemGroupInfoBinding.bind(holder.itemView)
        val person = personList[position]

        binding.tvPersonName.text = person.name
        binding.tvPersonStatus.text = person.status
    }

    override fun getItemCount(): Int = personList.size

    inner class ViewHolder(binding: ItemGroupInfoBinding) : RecyclerView.ViewHolder(binding.root)
}