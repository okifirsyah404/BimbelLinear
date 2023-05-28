package com.okifirsyah.bimbellinear.presentation.view.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.okifirsyah.bimbellinear.BuildConfig
import com.okifirsyah.bimbellinear.databinding.FragmentSplashBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    val viewModel: SplashViewModel by inject()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
        binding.tvSplashVersion.text = "v ${BuildConfig.VERSION_NAME}"
    }

    override fun initProcess() {
        Handler(Looper.getMainLooper()).postDelayed({
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.getFirstLaunch().observe(
                        viewLifecycleOwner
                    ) { isFirstLaunch: Boolean ->
                        if (isFirstLaunch) {
                            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment())
                        } else {
                            viewModel.getAuthToken().observe(
                                viewLifecycleOwner
                            ) { token: String ->
                                if (token.isNotEmpty()) {
                                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
                                } else {
                                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSignInFragment())
                                }
                            }
                        }
                    }

                }
            }
        }, 3000)

    }

    override fun initObservers() {
    }

}