package com.okifirsyah.bimbellinear.presentation.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.okifirsyah.bimbellinear.BuildConfig
import com.okifirsyah.bimbellinear.data.model.BookModel
import com.okifirsyah.bimbellinear.databinding.ItemModuleBookBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var bookList: ArrayList<BookModel> = ArrayList()

    fun setData(list: ArrayList<BookModel>) {
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

        val glideUrl = GlideUrl(
            "${BuildConfig.BASE_URL}/modul/thumb?img=${book.image}",
            LazyHeaders.Builder()
                .addHeader("Authorization", "")
                .build()
        )

        Glide.with(holder.itemView.context)
            .load(glideUrl)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.imgBookItemCover)

        binding.apply {
            tvBookItemTitle.text = book.title
            tvBookItemGrade.text = book.subTitle

            root.setOnClickListener {
                intentToWeb(holder.itemView.context, book.fileUrl!!)
            }
        }
    }

    inner class BookViewHolder(binding: ItemModuleBookBinding) :
        RecyclerView.ViewHolder(binding.root)

    private fun intentToWeb(context: Context, fileId: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("${BuildConfig.BASE_URL}/modul/pdf?modul=$fileId")
        startActivity(context, intent, null)
    }

}