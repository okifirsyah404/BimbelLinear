package com.okifirsyah.bimbellinear.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.data.model.PersonDevModel
import com.okifirsyah.bimbellinear.databinding.ItemGroupInfoBinding

class DevAdapter : RecyclerView.Adapter<DevAdapter.ViewHolder>() {

    private var personList: ArrayList<PersonDevModel> = ArrayList()

    fun setData(list: ArrayList<PersonDevModel>) {
        personList.clear()
        personList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevAdapter.ViewHolder {
        val binding =
            ItemGroupInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DevAdapter.ViewHolder, position: Int) {
        val binding = ItemGroupInfoBinding.bind(holder.itemView)
        val person = personList[position]

        Glide.with(holder.itemView)
            .load(person.photo)
            .error(R.drawable.avatar_oki)
            .circleCrop()
            .into(binding.civPerson)

        binding.tvPersonName.text = person.name
        binding.tvPersonStatus.text = person.role

    }

    override fun getItemCount(): Int = personList.size

    inner class ViewHolder(binding: ItemGroupInfoBinding) : RecyclerView.ViewHolder(binding.root)
}