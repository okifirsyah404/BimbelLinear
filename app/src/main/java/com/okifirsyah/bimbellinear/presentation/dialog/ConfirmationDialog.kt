package com.okifirsyah.bimbellinear.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.DialogConfirmationBinding

class ConfirmationDialog(
    private val dialogTitle: String,
    private val dialogMessage: String? = null,
    private val animationType: String = SingleButtonDialog.SUCCESS_DIALOG,
    private val isNeedAnimation: Boolean = false,
    private val onClickRight: (() -> Unit?)? = null,
    private val rightButtonText: String = "Ok",
    private val onClickLeft: (() -> Unit?)? = null,
    private val leftButtonText: String = "Cancel",
) : DialogFragment() {
    private var _binding: DialogConfirmationBinding? = null
    val binding get() = _binding!!

    companion object {
        const val TAG = "ConfirmationDialog"
        const val FAILED_ANIMATION = "FailedDialog"
        const val SUCCESS_ANIMATION = "SuccessDialog"
        const val NO_ANIMATION = "NoAnimation"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogConfirmationBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvDialogTitle.text = dialogTitle
        binding.tvDialogMessage.text = dialogMessage
        if (dialogMessage.isNullOrEmpty()) {
            binding.tvDialogMessage.visibility = View.GONE
        }


        binding.btnRightDialog.apply {
            text = rightButtonText
            setOnClickListener {
                onClickRight?.invoke()
                dismiss()
            }
        }

        binding.btnLeftDialog.apply {
            text = leftButtonText
            setOnClickListener {
                onClickLeft?.invoke()
                dismiss()
            }
        }

        dialog?.window?.setBackgroundDrawable(resources.getDrawable(android.R.color.transparent))
        initAnimation()
        changeLottieAnimation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeLottieAnimation() {
        if (animationType == FAILED_ANIMATION) {
            binding.lottieAnimationViewSuccess.visibility = View.GONE
            binding.lottieAnimationViewFailed.visibility = View.VISIBLE

            binding.btnRightDialog.setBackgroundColor(resources.getColor(R.color.error_50))
        } else if (animationType == SUCCESS_ANIMATION) {
            binding.lottieAnimationViewFailed.visibility = View.GONE
            binding.lottieAnimationViewSuccess.visibility = View.VISIBLE
        } else {
            binding.lottieAnimationViewFailed.visibility = View.GONE
            binding.lottieAnimationViewSuccess.visibility = View.GONE
        }
    }

    private fun initAnimation() {
        if (isNeedAnimation) {
            binding.lottieAnimationViewSuccess.visibility = View.VISIBLE
            binding.lottieAnimationViewFailed.visibility = View.VISIBLE
        }
    }
}