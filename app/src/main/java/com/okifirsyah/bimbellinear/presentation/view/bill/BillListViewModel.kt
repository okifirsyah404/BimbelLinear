package com.okifirsyah.bimbellinear.presentation.view.bill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okifirsyah.bimbellinear.data.local.preferences.AppPreferences
import com.okifirsyah.bimbellinear.data.model.BillModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseListResponse
import com.okifirsyah.bimbellinear.data.repository.BillRepository
import kotlinx.coroutines.launch

class BillListViewModel(private val pref: AppPreferences, private val repository: BillRepository) :
    ViewModel() {
    val userToken: MutableLiveData<String> by lazy { _userToken }
    private val _userToken = MutableLiveData<String>()

    val billResult: LiveData<ApiResponse<BaseListResponse<BillModel>>> by lazy { _billResult }
    private val _billResult = MutableLiveData<ApiResponse<BaseListResponse<BillModel>>>()

    fun getUserToken() {
        viewModelScope.launch {
            pref.getAuthToken().collect {
                _userToken.postValue(it)
            }
        }
    }

    fun fetchBill() {
        viewModelScope.launch {
            repository.fetchBills().collect {
                _billResult.postValue(it)
            }
        }
    }
}