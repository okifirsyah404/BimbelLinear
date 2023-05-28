package com.okifirsyah.bimbellinear.presentation.view.profile

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.okifirsyah.bimbellinear.BuildConfig
import com.okifirsyah.bimbellinear.data.local.preferences.AppPreferences
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.data.network.base.BaseResponse
import com.okifirsyah.bimbellinear.data.network.response.SupportContactResponse
import com.okifirsyah.bimbellinear.data.repository.UserRepository
import kotlinx.coroutines.launch
import net.gotev.uploadservice.data.UploadInfo
import net.gotev.uploadservice.network.ServerResponse
import net.gotev.uploadservice.observer.request.RequestObserverDelegate
import net.gotev.uploadservice.protocols.multipart.MultipartUploadRequest
import timber.log.Timber
import java.io.File

class ProfileViewModel(
    private val pref: AppPreferences,
    private val userRepository: UserRepository
) : ViewModel() {

    val userToken: MutableLiveData<String> by lazy { _userToken }
    private val _userToken = MutableLiveData<String>()

    val contactSupportData: MutableLiveData<ApiResponse<BaseResponse<SupportContactResponse>>> by lazy { _contactSupportData }
    private val _contactSupportData =
        MutableLiveData<ApiResponse<BaseResponse<SupportContactResponse>>>()

    val logoutData: MutableLiveData<ApiResponse<BaseResponse<Nothing>>> by lazy { _logoutData }
    private val _logoutData = MutableLiveData<ApiResponse<BaseResponse<Nothing>>>()

    fun setAuthToken(token: String) {
        viewModelScope.launch {
            pref.saveAuthToken(token)
        }
    }

    fun getAuthToken() {
        viewModelScope.launch {
            pref.getAuthToken().collect {
                _userToken.postValue(it)
            }
        }
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }

    fun uploadAvatar(context: Context, lifecycleOwner: LifecycleOwner, token: String, file: File) {
        viewModelScope.launch {
            Timber.tag("URI_LOC_VIEW_MODEL").d("onActivityResult: " + file)
            MultipartUploadRequest(context, "${BuildConfig.BASE_URL}uploadimg")
                .setMethod("POST")
                .addHeader("Authorization", token)
                .setMaxRetries(2)
                .addFileToUpload(file.path, "file", file.name)
                .subscribe(
                    context = context,
                    lifecycleOwner = lifecycleOwner,
                    delegate = object : RequestObserverDelegate {
                        override fun onCompleted(context: Context, uploadInfo: UploadInfo) {
                            Timber.tag("UPLOAD_AVATAR").d("onCompleted: " + uploadInfo)
                        }

                        override fun onCompletedWhileNotObserving() {
                            Timber.tag("UPLOAD_AVATAR").d("onCompletedWhileNotObserving: ")
                        }

                        override fun onError(
                            context: Context,
                            uploadInfo: UploadInfo,
                            exception: Throwable
                        ) {
                            exception.stackTrace
                            Timber.tag("UPLOAD_AVATAR").d("onError: " + exception)
                        }

                        override fun onProgress(context: Context, uploadInfo: UploadInfo) {
                            Timber.tag("UPLOAD_AVATAR").d("onProgress: " + uploadInfo)
                        }

                        override fun onSuccess(
                            context: Context,
                            uploadInfo: UploadInfo,
                            serverResponse: ServerResponse
                        ) {
                            Timber.tag("UPLOAD_AVATAR").d("onSuccess: " + serverResponse.bodyString)
                        }

                    })
        }
    }

    fun contactSupport() {
        viewModelScope.launch {
            userRepository.getSupportContact().collect {
                _contactSupportData.postValue(it)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout().collect {
                _logoutData.postValue(it)
            }
        }
    }
}