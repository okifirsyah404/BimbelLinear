package com.okifirsyah.bimbellinear.presentation.view.otp

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.databinding.FragmentOtpBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.extensions.showHttpErrorDialog
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.text.DecimalFormat
import java.text.NumberFormat

class OtpFragment : BaseFragment<FragmentOtpBinding>() {

    private val viewModel: OtpViewModel by inject()

    private val args: OtpFragmentArgs by navArgs()
    private var countDownTimer: CountDownTimer? = null
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOtpBinding {
        return FragmentOtpBinding.inflate(inflater, container, false)
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.title = pageTitleConstant.OTP
        binding.toolbar.mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initUI() {
        initCountDownTimer()
    }

    override fun initProcess() {
        when {
            args.userArgs != null -> {
                initChangePassword()
            }

            args.userEmailArgs != null -> {
                initResetPassword()
            }

            else -> {
                Timber.tag("OTP").d("init OTP: Null")
            }
        }
    }

    override fun initObservers() {
    }

    override fun initIntent() {
    }

    override fun onClose() {
        super.onClose()
        countDownTimer?.cancel()
    }

    private fun initLoading(isLoading: Boolean) {
        if (isLoading) binding.apply {
            llMainContent.visibility = View.GONE
            otpLoading.layoutLoading.visibility = View.VISIBLE
        } else binding.apply {
            llMainContent.visibility = View.VISIBLE
            otpLoading.layoutLoading.visibility = View.GONE
        }
    }

    private fun initChangePassword() {
        binding.apply {
            txtUserEmail.text = args.userArgs?.email
            Timber.tag("OTP").d("initChangePassword: ${args.userArgs?.email}")

            getChangePasswordOtp()

            btnVerify.setOnClickListener {
                val otp = binding.pvOtp.text.toString()
                Timber.tag("OTP").d("OTP IS: ${otp}")
                sendOtpChangePassword(otp)
            }
        }
    }

    private fun getChangePasswordOtp() {
        viewModel.getOtpChangePassword().observe(viewLifecycleOwner) {
            when (it) {

                is ApiResponse.Loading -> {
                    Timber.tag("OTP").d("initChangePassword: Loading")
                    initLoading(true)
                }

                is ApiResponse.Success -> {
                    Timber.tag("OTP").d("initChangePassword: Nothing")
                    initLoading(false)
                }

                is ApiResponse.Error -> {
                    Timber.tag("OTP").d("initChangePassword: ${it.errorMessage}")
                    showHttpErrorDialog(it.errorMessage)
                }

                else -> {
                    Timber.tag("OTP").d("initChangePassword: Loading Else")
                }
            }
        }
    }

    private fun sendOtpChangePassword(otp: String) {
        viewModel.sendOtpChangePassword(otp).observe(viewLifecycleOwner) {
            when (it) {

                is ApiResponse.Loading -> {
                    Timber.tag("OTP").d("sendOtpChangePassword: Loading")
                }

                is ApiResponse.Success -> {
                    Timber.tag("OTP").d("sendOtpChangePassword: Success")
                    findNavController().navigate(
                        OtpFragmentDirections.actionOtpFragmentToChangePasswordFragment(userArgs = args.userArgs)
                    )
                }

                is ApiResponse.Error -> {
                    Timber.tag("OTP").d("sendOtpChangePassword: ${it.errorMessage}")
                    showHttpErrorDialog(it.errorMessage)
                }

                else -> {
                    Timber.tag("OTP").d("sendOtpChangePassword: Else")
                }
            }
        }
    }

    private fun initResetPassword() {
        binding.apply {
            initLoading(false)
            txtUserEmail.text = args.userEmailArgs
            Timber.tag("OTP").d("initResetPassword: ${args.userArgs?.email}")

            btnVerify.setOnClickListener {
                val otp = binding.pvOtp.text.toString()
                Timber.tag("OTP").d("OTP IS: $otp")
                sendOtpResetPassword(otp)
            }
        }
    }

    private fun sendOtpResetPassword(otp: String) {
        viewModel.sendOtpResetPassword(otp).observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    Timber.tag("OTP").d("resetOtpChangePassword: Loading")
                }

                is ApiResponse.Success -> {
                    Timber.tag("OTP").d("resetOtpChangePassword: Success")
                    findNavController().navigate(
                        OtpFragmentDirections.actionOtpFragmentToChangePasswordFragment(
                            userEmailArgs = args.userEmailArgs
                        )
                    )
                }

                is ApiResponse.Error -> {
                    Timber.tag("OTP").d("resetOtpChangePassword: ${it.errorMessage}")
                    showHttpErrorDialog(it.errorMessage)
                }

                else -> {
                    Timber.tag("OTP").d("resetOtpChangePassword: Else")
                }
            }
        }
    }

    private fun initCountDownTimer() {
        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val format: NumberFormat = DecimalFormat("00")
                val sec = millisUntilFinished / 1000 % 60
                binding.btnResend.apply {
                    val formattedSec = format.format(sec)
                    if (formattedSec.toInt() >= 10) text = "${format.format(sec)} detik"
                    setOnClickListener(null)
                }
            }

            override fun onFinish() {
                binding.btnResend.apply {
                    text = "Kirim Ulang"
                    setOnClickListener {
                        initCountDownTimer()
                        initProcess()
                    }
                }
            }
        }.start()
    }


}