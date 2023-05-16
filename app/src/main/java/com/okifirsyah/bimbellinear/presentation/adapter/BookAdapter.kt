package com.okifirsyah.bimbellinear.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.data.model.ModuleBookModel
import com.okifirsyah.bimbellinear.databinding.ItemModuleBookBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var bookList: ArrayList<ModuleBookModel> = ArrayList()

    fun setData(list: ArrayList<ModuleBookModel>) {
        bookList.clear()
        bookList.addAll(list)
//        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.BookViewHolder {
        val binding =
            ItemModuleBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val binding = ItemModuleBookBinding.bind(holder.itemView)
        val book = bookList[position]

        Glide.with(holder.itemView.context)
            .load(R.drawable.splash_1)
            .into(binding.imgBookItemCover)

        binding.tvBookItemTitle.text = book.title
        binding.tvBookItemGrade.text = book.grade
    }


    inner class BookViewHolder(binding: ItemModuleBookBinding) :
        RecyclerView.ViewHolder(binding.root)

}