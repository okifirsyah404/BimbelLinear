package com.okifirsyah.bimbellinear.utils.extensions

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.okifirsyah.bimbellinear.BuildConfig
import com.okifirsyah.bimbellinear.presentation.dialog.CameraOrGalleryDialog
import com.okifirsyah.bimbellinear.presentation.dialog.ConfirmationDialog
import com.okifirsyah.bimbellinear.presentation.dialog.SingleButtonDialog
import com.okifirsyah.bimbellinear.utils.constant.dialogConstant.ERROR_TITLE

fun Fragment.showCustomDialog(
    title: String,
    message: String? = null,
    onSubmit: (() -> Unit?)? = null,
    submitText: String = "Ok",
    dialogType: String = SingleButtonDialog.DIALOG_NO_ANIMATION,
    isNeedAnimation: Boolean = false,
) {
    SingleButtonDialog(
        title,
        dialogType = dialogType,
        dialogMessage = message,
        isNeedAnimation = isNeedAnimation,
        onSubmit = onSubmit,
        buttonText = submitText
    ).show(childFragmentManager, SingleButtonDialog.TAG)
}

fun Fragment.showCustomConfirmationDialog(
    title: String,
    message: String? = null,
    onSubmit: (() -> Unit?)? = null,
    submitText: String = "Ok",
    onCancel: (() -> Unit?)? = null,
    cancelText: String = "Ok",
    dialogType: String = ConfirmationDialog.NO_ANIMATION,
    isNeedAnimation: Boolean = false,
) {
    ConfirmationDialog(
        title,
        animationType = dialogType,
        dialogMessage = message,
        isNeedAnimation = isNeedAnimation,
        onClickRight = onSubmit,
        rightButtonText = submitText,
        leftButtonText = cancelText,
        onClickLeft = onCancel
    ).show(childFragmentManager, ConfirmationDialog.TAG)
}

fun Fragment.showCameraOrGalleryDialog(launcher: ActivityResultLauncher<Intent>) {
    CameraOrGalleryDialog(launcher).show(childFragmentManager, CameraOrGalleryDialog.TAG)
}

fun Fragment.showHttpErrorDialog(
    errorMessage: String,
    submitText: String = "OK",
    onSubmit: (() -> Unit?)? = null
) {
    if ("Unable to resolve host" in errorMessage) {
        SingleButtonDialog(
            ERROR_TITLE,
            dialogMessage = "Harap periksa koneksi internet anda",
            onSubmit = onSubmit,
            buttonText = submitText,
            dialogType = SingleButtonDialog.FAILED_DIALOG
        ).show(childFragmentManager, SingleButtonDialog.TAG)
    } else {
        SingleButtonDialog(
            ERROR_TITLE,
            dialogMessage = errorMessage,
            onSubmit = onSubmit,
            buttonText = submitText,
            dialogType = SingleButtonDialog.FAILED_DIALOG
        ).show(childFragmentManager, SingleButtonDialog.TAG)
    }
}

fun Fragment.intentToPackageSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
    intent.data = uri
    startActivity(intent)
}

