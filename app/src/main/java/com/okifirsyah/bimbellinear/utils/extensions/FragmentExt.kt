package com.okifirsyah.bimbellinear.utils.extensions

import androidx.fragment.app.Fragment
import com.okifirsyah.bimbellinear.presentation.dialog.ConfirmationDialog
import com.okifirsyah.bimbellinear.presentation.dialog.SingleButtonDialog

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
