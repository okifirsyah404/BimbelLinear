package com.okifirsyah.bimbellinear.presentation.view.sign_in

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.databinding.FragmentSignInBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.presentation.dialog.SingleButtonDialog
import com.okifirsyah.bimbellinear.utils.constant.dialogConstant
import com.okifirsyah.bimbellinear.utils.extensions.isEmail
import com.okifirsyah.bimbellinear.utils.extensions.showCustomDialog
import com.okifirsyah.bimbellinear.utils.extensions.showHttpErrorDialog
import org.koin.android.ext.android.inject
import timber.log.Timber

class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val viewModel: SignInViewModel by inject()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
    }

    override fun initProcess() {
        resetToken()
    }

    override fun initObservers() {
    }

    override fun initIntent() {
        binding.btnlogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

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

                        password.isBlank() -> {
                            showCustomDialog(
                                dialogConstant.ERROR_PASSWORD_SIGN_IN_TITLE,
                                "Silahkan masukkan password dengan benar",
                                dialogType = SingleButtonDialog.FAILED_DIALOG
                            )
                        }

                        else -> loginUser(email, password)
                    }
                }, 200L
            )
        }
    }


    private fun loginUser(email: String, password: String) {
        viewModel.loginUser(email, password).observe(viewLifecycleOwner) { response ->
            when (response) {
                is ApiResponse.Loading -> {
                    Timber.d("Loading")
                }

                is ApiResponse.Error -> {
                    Timber.d("Error ${response.errorMessage}")
                    if (response.errorMessage == "Pengguna tidak ditemukan")
                        showCustomDialog(
                            dialogConstant.ERROR_TITLE,
                            "Email atau password salah",
                            dialogType = SingleButtonDialog.FAILED_DIALOG
                        )
                    else showHttpErrorDialog(response.errorMessage)
                }

                is ApiResponse.Success -> {
                    Timber.tag("AUTH_TOKEN").d("token is: " + (response.data.data?.token ?: "null"))
                    viewModel.saveUserToken(response.data.data?.token ?: "")
                    findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
                }

                else -> {
                    Timber.d("Unknown Error")
                }
            }
        }
    }

    private fun resetToken() {
        viewModel.saveUserToken("")
    }


}