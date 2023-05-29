package com.okifirsyah.bimbellinear.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.okifirsyah.bimbellinear.BuildConfig
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.data.model.MemberGroupModel
import com.okifirsyah.bimbellinear.databinding.ItemGroupInfoBinding
import com.okifirsyah.bimbellinear.utils.extensions.toTitleCase

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private var personList: ArrayList<MemberGroupModel> = ArrayList()

    fun setData(list: ArrayList<MemberGroupModel>) {
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

        Glide.with(holder.itemView)
            .load("${BuildConfig.BASE_URL}/img?img=${person.id}.jpg")
            .error(R.drawable.default_avatar)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .circleCrop()
            .into(binding.civPerson)

        binding.tvPersonName.text = person.name
        binding.tvPersonStatus.text = person.memberStatus?.toTitleCase()
    }

    override fun getItemCount(): Int = personList.size

    inner class ViewHolder(binding: ItemGroupInfoBinding) : RecyclerView.ViewHolder(binding.root)
}