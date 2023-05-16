package com.okifirsyah.bimbellinear.presentation.view.module_book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.okifirsyah.bimbellinear.data.model.ModuleBookModel
import com.okifirsyah.bimbellinear.databinding.FragmentModuleBookBinding
import com.okifirsyah.bimbellinear.presentation.adapter.BookAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant

class ModuleBookFragment : BaseFragment<FragmentModuleBookBinding>() {

    private val bookAdapter: BookAdapter by lazy { BookAdapter() }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentModuleBookBinding {
        return FragmentModuleBookBinding.inflate(inflater, container, false)
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.title = pageTitleConstant.BOOK_MODULES
        binding.toolbar.mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initUI() {
        binding.rvModule.layoutManager = GridLayoutManager(context, 2)

        val dummyBook = setDummyData()

//        Move to observers
        binding.rvModule.adapter = bookAdapter
        bookAdapter.setData(
            dummyBook
        )
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    private fun setDummyData(): ArrayList<ModuleBookModel> {
        val book = ArrayList<ModuleBookModel>()
        book.add(ModuleBookModel(1, "1", "1"))
        book.add(ModuleBookModel(2, "2", "2"))
        book.add(ModuleBookModel(3, "3", "3"))
        book.add(ModuleBookModel(4, "4", "4"))
        book.add(ModuleBookModel(5, "5", "5"))
        book.add(ModuleBookModel(6, "6", "6"))
        book.add(ModuleBookModel(7, "7", "7"))
        book.add(ModuleBookModel(8, "8", "8"))

        return book
    }

}