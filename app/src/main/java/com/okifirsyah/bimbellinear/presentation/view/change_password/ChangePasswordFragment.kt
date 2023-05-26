package com.okifirsyah.bimbellinear.presentation.view.change_password

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.databinding.FragmentChangePasswordBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.presentation.dialog.SingleButtonDialog
import com.okifirsyah.bimbellinear.utils.constant.dialogConstant
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.extensions.isStrongPassword
import com.okifirsyah.bimbellinear.utils.extensions.showCustomDialog
import com.okifirsyah.bimbellinear.utils.extensions.showHttpErrorDialog
import org.koin.android.ext.android.inject
import timber.log.Timber

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>() {

    private val viewModel: ChangePasswordViewModel by inject()
    private val args: ChangePasswordFragmentArgs by navArgs()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentChangePasswordBinding {
        return FragmentChangePasswordBinding.inflate(inflater, container, false)
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.apply {
            title = if (args.userArgs != null) {
                pageTitleConstant.CHANGE_PASSWORD
            } else {
                pageTitleConstant.RESET_PASSWORD
            }
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun initUI() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    override fun initIntent() {
        binding.btnChangePassword.setOnClickListener {
            val newPassword = binding.etNewPassword.text.toString()
            val reEnterNewPassword = binding.etReEnterNewPassword.text.toString()
            validateForm(newPassword, reEnterNewPassword)
        }
    }

    private fun changePassword(newPassword: String) {
        viewModel.changePassword(newPassword).observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    Timber.d("changePassword: loading")
                }

                is ApiResponse.Success -> {
                    Timber.d("changePassword: success")
                    showCustomDialog(
                        dialogConstant.SUCCESS_PASSWORD_CHANGE_TITLE,
                        "Kata sandi berhasil diubah",
                        dialogType = SingleButtonDialog.SUCCESS_DIALOG,
                        submitText = "Kembali",
                        onSubmit = {
                            onBackToProfile()
                        }
                    )
                }

                is ApiResponse.Error -> {
                    Timber.d("changePassword: ${it.errorMessage}")
                    showHttpErrorDialog(it.errorMessage)
                }

                else -> {
                    Timber.d("changePassword: else")
                }
            }
        }
    }

    private fun resetPassword(email: String, newPassword: String) {
        viewModel.resetPassword(email, newPassword).observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    Timber.d("resetPassword: loading")
                }

                is ApiResponse.Success -> {
                    Timber.d("resetPassword: success")
                    showCustomDialog(
                        dialogConstant.SUCCESS_PASSWORD_CHANGE_TITLE,
                        "Kata sandi berhasil diubah",
                        dialogType = SingleButtonDialog.SUCCESS_DIALOG,
                        submitText = "Kembali",
                        onSubmit = {
                            onBackToSignIn()
                        }
                    )
                }

                is ApiResponse.Error -> {
                    Timber.d("resetPassword: ${it.errorMessage}")
                    showHttpErrorDialog(it.errorMessage)
                }

                else -> {
                    Timber.d("resetPassword: else")
                }
            }
        }
    }

    private fun validateForm(password: String, rePassword: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            when {
                password.isBlank() -> {
                    showCustomDialog(
                        dialogConstant.EMPTY_PASSWORD_TITLE,
                        "Silahkan masukkan kata sandi dengan benar",
                        dialogType = SingleButtonDialog.FAILED_DIALOG
                    )
                }

                rePassword.isBlank() -> {
                    showCustomDialog(
                        dialogConstant.EMPTY_PASSWORD_TITLE,
                        "Silahkan masukkan ulangi kata sandi dengan benar",
                        dialogType = SingleButtonDialog.FAILED_DIALOG
                    )
                }

                password.length < 8 -> {
                    showCustomDialog(
                        dialogConstant.WEAK_PASSWORD_TITLE,
                        "Kata sandi minimal 8 karakter",
                        dialogType = SingleButtonDialog.FAILED_DIALOG
                    )
                }

                !password.isStrongPassword() -> {
                    showCustomDialog(
                        dialogConstant.WEAK_PASSWORD_TITLE,
                        "Kata sandi harus mengandung huruf dan angka",
                        dialogType = SingleButtonDialog.FAILED_DIALOG
                    )
                }

                rePassword.length < 8 -> {
                    showCustomDialog(
                        dialogConstant.WEAK_PASSWORD_TITLE,
                        "Kata sandi minimal 8 karakter",
                        dialogType = SingleButtonDialog.FAILED_DIALOG
                    )
                }

                password != rePassword -> {
                    showCustomDialog(
                        dialogConstant.WEAK_PASSWORD_TITLE,
                        "Ulangi kata sandi tidak sama dengan kata sandi baru",
                        dialogType = SingleButtonDialog.FAILED_DIALOG
                    )
                }

                else -> {

                    when {
                        args.userArgs != null -> {
                            changePassword(password)
                        }

                        args.userEmailArgs != null -> {
                            resetPassword(args.userEmailArgs!!, password)
                        }

                        else -> {
                            Timber.d("changePassword: else")
                        }
                    }
                }
            }
        }, 200L)

    }

    private fun onBackToProfile() {
        findNavController().popBackStack(R.id.profileFragment, false)
    }

    private fun onBackToSignIn() {
        findNavController().popBackStack(R.id.signInFragment, false)
    }
}