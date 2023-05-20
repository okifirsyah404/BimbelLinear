package com.okifirsyah.bimbellinear.presentation.view.group_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okifirsyah.bimbellinear.data.local.preferences.AppPreferences
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.network.response.GroupResponse
import com.okifirsyah.bimbellinear.data.repository.GroupRepository
import kotlinx.coroutines.launch

class GroupInfoViewModel(
    private val pref: AppPreferences,
    private val repository: GroupRepository
) : ViewModel() {

    val userToken: MutableLiveData<String> by lazy { _userToken }
    private val _userToken = MutableLiveData<String>()

    val groupResult: LiveData<ApiResponse<BaseResponse<GroupResponse>>> by lazy { _groupResult }
    private val _groupResult = MutableLiveData<ApiResponse<BaseResponse<GroupResponse>>>()

    fun getUserToken() {
        viewModelScope.launch {
            pref.getAuthToken().collect {
                _userToken.postValue(it)
            }
        }
    }

    fun fetchGroup() {
        viewModelScope.launch {
            repository.fetchGroupData().collect {
                _groupResult.postValue(it)
            }
        }
    }
}