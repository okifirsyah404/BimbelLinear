package com.okifirsyah.bimbellinear.utils.extensions

import androidx.fragment.app.Fragment
import com.okifirsyah.bimbellinear.presentation.dialog.SingleButtonDialog

fun Fragment.showSuccessSingleButtonDialogWithAnimation(title: String, message: String? = null) {
    SingleButtonDialog(
        title,
        animationType = SingleButtonDialog.SUCCESS_DIALOG,
        dialogMessage = message,
        isNeedAnimation = true
    ).show(childFragmentManager, SingleButtonDialog.TAG)
}

fun Fragment.showErrorSingleButtonDialogWithAnimation(title: String, message: String? = null) {
    SingleButtonDialog(
        title,
        animationType = SingleButtonDialog.FAILED_DIALOG,
        dialogMessage = message,
        isNeedAnimation = true
    ).show(childFragmentManager, SingleButtonDialog.TAG)
}

fun Fragment.showSuccessSingleButtonDialog(title: String, message: String? = null) {
    SingleButtonDialog(
        title,
        dialogMessage = message,
        isNeedAnimation = false
    ).show(childFragmentManager, SingleButtonDialog.TAG)
}

fun Fragment.showErrorSingleButtonDialog(title: String, message: String? = null) {
    SingleButtonDialog(
        title,
        dialogMessage = message,
        isNeedAnimation = false
    ).show(childFragmentManager, SingleButtonDialog.TAG)
}

