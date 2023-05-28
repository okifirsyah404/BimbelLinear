package com.okifirsyah.bimbellinear.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.DialogSingleButtonBinding

class SingleButtonDialog(
    private val dialogTitle: String,
    private val dialogMessage: String? = null,
    private val dialogType: String = SUCCESS_DIALOG,
    private val isNeedAnimation: Boolean = false,
    private val buttonText: String = "Ok",
    private val onSubmit: (() -> Unit?)? = null
) : DialogFragment() {

    private var _binding: DialogSingleButtonBinding? = null
    val binding get() = _binding!!

    companion object {
        const val TAG = "SingleButtonDialog"
        const val FAILED_DIALOG = "FailedDialog"
        const val SUCCESS_DIALOG = "SuccessDialog"
        const val DIALOG_NO_ANIMATION = "NoAnimation"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogSingleButtonBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvDialogTitle.text = dialogTitle
        binding.tvDialogMessage.text = dialogMessage
        if (dialogMessage.isNullOrEmpty()) {
            binding.tvDialogMessage.visibility = View.GONE
        }


        binding.btnConfirmDialog.apply {
            text = buttonText
            setOnClickListener {
                onSubmit?.invoke()
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
        if (dialogType == FAILED_DIALOG) {
            binding.lottieAnimationViewSuccess.visibility = View.GONE
            binding.lottieAnimationViewFailed.visibility = View.VISIBLE

            binding.btnConfirmDialog.setBackgroundColor(resources.getColor(R.color.error_50))
        } else if (dialogType == SUCCESS_DIALOG) {
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