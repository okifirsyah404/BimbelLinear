package com.okifirsyah.bimbellinear.presentation.view.bill_detail

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okifirsyah.bimbellinear.BuildConfig
import com.okifirsyah.bimbellinear.data.local.preferences.AppPreferences
import kotlinx.coroutines.launch
import net.gotev.uploadservice.data.UploadInfo
import net.gotev.uploadservice.network.ServerResponse
import net.gotev.uploadservice.observer.request.RequestObserverDelegate
import net.gotev.uploadservice.protocols.multipart.MultipartUploadRequest
import timber.log.Timber
import java.io.File

class BillDetailViewModel(private val pref: AppPreferences) : ViewModel() {

    val userToken: MutableLiveData<String> by lazy { _userToken }
    private val _userToken = MutableLiveData<String>()

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

    fun uploadBIll(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        token: String,
        file: File,
        billId: String,
        onSuccess: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null
    ) {
        viewModelScope.launch {
            Timber.tag("URI_LOC_VIEW_MODEL").d("onActivityResult: " + file)
            MultipartUploadRequest(context, "${BuildConfig.BASE_URL}tagihan/$billId")
                .setMethod("POST")
                .addHeader("Authorization", token)
                .setMaxRetries(2)
                .addFileToUpload(file.path, "file", file.name)
                .subscribe(
                    context = context,
                    lifecycleOwner = lifecycleOwner,
                    delegate = object : RequestObserverDelegate {
                        override fun onCompleted(context: Context, uploadInfo: UploadInfo) {
                            Timber.tag("UPLOAD_BILL").d("onCompleted: " + uploadInfo)
                        }

                        override fun onCompletedWhileNotObserving() {
                            Timber.tag("UPLOAD_BILL").d("onCompletedWhileNotObserving: ")
                        }

                        override fun onError(
                            context: Context,
                            uploadInfo: UploadInfo,
                            exception: Throwable
                        ) {
                            exception.stackTrace
                            Timber.tag("UPLOAD_BILL").d("onError: " + exception)
                            onError?.invoke(exception)
                        }

                        override fun onProgress(context: Context, uploadInfo: UploadInfo) {
                            Timber.tag("UPLOAD_BILL").d("onProgress: " + uploadInfo)
                        }

                        override fun onSuccess(
                            context: Context,
                            uploadInfo: UploadInfo,
                            serverResponse: ServerResponse
                        ) {
                            Timber.tag("UPLOAD_BILL").d("onSuccess: " + serverResponse.bodyString)
                            onSuccess?.invoke()
                        }

                    })
        }
    }
}