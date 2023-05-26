package com.okifirsyah.bimbellinear.presentation.view.reset_password

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.databinding.FragmentResetPasswordBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.presentation.dialog.SingleButtonDialog
import com.okifirsyah.bimbellinear.utils.constant.dialogConstant
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.extensions.isEmail
import com.okifirsyah.bimbellinear.utils.extensions.showCustomDialog
import com.okifirsyah.bimbellinear.utils.extensions.showHttpErrorDialog
import org.koin.android.ext.android.inject
import timber.log.Timber

class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding>() {

    private val viewModel: ResetPasswordViewModel by inject()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentResetPasswordBinding {
        return FragmentResetPasswordBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    override fun initIntent() {
        verifyEmail()
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.apply {
            title = pageTitleConstant.FORGET_PASSWORD
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun verifyEmail() {
        binding.apply {
            btnResetPassword.setOnClickListener {
                val email = etEmail.text.toString()
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        when {
                            email.isBlank() -> {
                                showCustomDialog(
                                    dialogConstant.ERROR_EMAIL_SIGN_IN_TITLE,
                                    "Silahkan masukkan email dengan benar",
                                    dialogType = SingleButtonDialog.FAILED_DIALOG
                                )
                            }

                            email.isEmail() -> {
                                showCustomDialog(
                                    dialogConstant.ERROR_EMAIL_SIGN_IN_TITLE,
                                    "Silahkan masukkan email dengan benar",
                                    dialogType = SingleButtonDialog.FAILED_DIALOG
                                )
                            }

                            else -> sendEmail(email)
                        }
                    }, 200L
                )
            }
        }
    }

    private fun sendEmail(email: String) {
        viewModel.getOtpResetPassword(email).observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Success -> {
                    findNavController().navigate(
                        ResetPasswordFragmentDirections.actionResetPasswordFragmentToOtpFragment(
                            userEmailArgs = email
                        )
                    )
                }

                is ApiResponse.Error -> {
                    showHttpErrorDialog(
                        it.errorMessage
                    )
                }

                else -> {
                    Timber.d("sendEmail: $it")
                }
            }
        }
    }


}