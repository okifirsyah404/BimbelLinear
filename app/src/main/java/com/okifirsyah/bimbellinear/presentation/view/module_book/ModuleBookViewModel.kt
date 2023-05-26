package com.okifirsyah.bimbellinear.presentation.view.module_book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okifirsyah.bimbellinear.data.model.BookModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import com.okifirsyah.bimbellinear.data.repository.BookRepository
import kotlinx.coroutines.launch

class ModuleBookViewModel(private val bookRepository: BookRepository) : ViewModel() {

    val booksResult: MutableLiveData<ApiResponse<BaseListResponse<BookModel>>> by lazy { _booksResult }
    private val _booksResult = MutableLiveData<ApiResponse<BaseListResponse<BookModel>>>()

    fun fetchBooks() {
        viewModelScope.launch {
            bookRepository.fetchBooks().collect {
                _booksResult.postValue(it)
            }
        }
    }
}