package com.okifirsyah.bimbellinear.presentation.view.module_book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.okifirsyah.bimbellinear.data.model.BookModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.databinding.FragmentModuleBookBinding
import com.okifirsyah.bimbellinear.presentation.adapter.BookAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.extensions.showHttpErrorDialog
import org.koin.android.ext.android.inject
import timber.log.Timber

class ModuleBookFragment : BaseFragment<FragmentModuleBookBinding>() {
    private val viewModel: ModuleBookViewModel by inject()
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

    }

    override fun initProcess() {
        viewModel.fetchBooks()
    }

    override fun initObservers() {
        initBooks()
    }

    private fun initLoading(isLoading: Boolean) {
        if (isLoading) binding.apply {
            rvModule.visibility = View.GONE
            bookLoading.layoutLoading.visibility = View.VISIBLE
        } else binding.apply {
            rvModule.visibility = View.VISIBLE
            bookLoading.layoutLoading.visibility = View.GONE
        }
    }

    private fun initBooks() {
        viewModel.booksResult.observe(viewLifecycleOwner) {
            when (it) {

                is ApiResponse.Loading -> {
                    initLoading(true)
                    Timber.tag("BookLoading").d(it.toString())
                }

                is ApiResponse.Success -> {

                    val responseData = it.data.data

                    Timber.tag("BookSuccess").d(responseData.toString())

                    if (responseData?.size!! > 0) {
                        binding.rvModule.adapter = bookAdapter
                        bookAdapter.setData(it.data.data as ArrayList<BookModel>)
                    } else {
                        binding.apply {
                            layoutEmptyItem.emptyView.visibility = View.VISIBLE
                            layoutEmptyItem.tvEmptyMessage.text =
                                "Tidak ada buku modul yang tersedia"
                            rvModule.visibility = View.GONE
                        }
                    }
                    initLoading(false)
                }

                is ApiResponse.Error -> {
                    Timber.tag("BookError").d(it.errorMessage)
                    showHttpErrorDialog(it.errorMessage)
                }

                else -> {
                    Timber.tag("BookErrorException").d(it.toString())
                }
            }
        }
    }

}