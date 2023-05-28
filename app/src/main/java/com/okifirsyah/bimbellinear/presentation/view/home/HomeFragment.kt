package com.okifirsyah.bimbellinear.presentation.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.okifirsyah.bimbellinear.BuildConfig
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.data.model.ScheduleModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.databinding.FragmentHomeBinding
import com.okifirsyah.bimbellinear.presentation.adapter.ScheduleAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.errorMessageConstant.NO_CONNECTION
import com.okifirsyah.bimbellinear.utils.constant.stateKeyConstant.KEY_PASSWORD_NOTIFICATION_CLOSED
import com.okifirsyah.bimbellinear.utils.extensions.getGreetings
import com.okifirsyah.bimbellinear.utils.extensions.showHttpErrorDialog
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.Calendar

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    companion object {
        private const val SAVED_INSTANCE_STATE_KEY = "saved_instance_state_key"
    }

    private val viewModel: HomeViewModel by inject()
    private val scheduleAdapter: ScheduleAdapter by lazy { ScheduleAdapter() }

    private var isPasswordNotificationClosed = false

    val savedInstanceState = arguments?.getBundle(SAVED_INSTANCE_STATE_KEY)

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
        binding.rvSchedules.layoutManager = LinearLayoutManager(context)
    }

    override fun initProcess() {
        viewModel.getUserToken()
        viewModel.getUserProfile()
        viewModel.fetchSchedules()
        viewModel.getChangePasswordNotification()
    }

    override fun initObservers() {
        initUserData()
        initSchedule()
    }

    override fun initIntent() {
    }

    override fun initAppBar() {
        binding.homeToolbar.tvGreetings.text = Calendar.getInstance().getGreetings()
    }

    override fun initOnRefresh() {
        binding.llRefreshHome.setOnRefreshListener {
            initIntent()
            initUI()
            initAppBar()
            initProcess()
            initObservers()
            initOnRefresh()
            binding.llRefreshHome.isRefreshing = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean(KEY_PASSWORD_NOTIFICATION_CLOSED, isPasswordNotificationClosed)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        if (savedInstanceState != null) {
            isPasswordNotificationClosed =
                savedInstanceState.getBoolean(KEY_PASSWORD_NOTIFICATION_CLOSED, false)
        }
    }

    private fun initLoading(isLoading: Boolean) {
        if (isLoading) binding.apply {
            llMainContent.visibility = View.GONE
            homeLoading.layoutLoading.visibility = View.VISIBLE
        } else binding.apply {
            llMainContent.visibility = View.VISIBLE
            homeLoading.layoutLoading.visibility = View.GONE
        }
    }

    private fun initUserData() {
        viewModel.userProfileResult.observe(viewLifecycleOwner) { response ->
            when (response) {

                is ApiResponse.Loading -> {
                    Timber.d("Loading")
                    initLoading(true)
                }

                is ApiResponse.Success -> {
                    initLoading(false)

                    val userResponse = response.data.data

                    binding.homeToolbar.tvPersonName.text = userResponse?.name

                    Glide.with(this)
                        .load("${BuildConfig.BASE_URL}/img?img=${userResponse?.id}.jpg")
                        .error(R.drawable.default_avatar)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .circleCrop()
                        .into(binding.homeToolbar.civPersonAvatar)

                    when (response.data.status) {
                        200 -> {
                            binding.apply {
                                homeToolbar.btnProfile.setOnClickListener {
                                    val navDirections =
                                        HomeFragmentDirections.actionHomeFragmentToProfileFragment(
                                            userResponse
                                        )
                                    findNavController().navigate(navDirections)
                                }
                                lNotifyOffline.visibility = View.GONE
                            }
                        }

                        503 -> {
                            binding.apply {
                                homeToolbar.btnProfile.setOnClickListener(null)
                                lNotifyOffline.visibility = View.VISIBLE
                            }
                        }

                        else -> {
                            binding.homeToolbar.btnProfile.setOnClickListener(null)
                        }
                    }
                }

                is ApiResponse.Error -> {
                    Timber.tag("Error HOME").d(response.errorMessage)
                    viewModel.getLocalProfile()

                    if (NO_CONNECTION in response.errorMessage) {
                        showHttpErrorDialog(
                            response.errorMessage,
                            submitText = "Masuk Ke Offline Mode",
                        )
                    } else {
                        showHttpErrorDialog(
                            response.errorMessage,
                            submitText = "Login Kembali",
                            onSubmit = {
                                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSignInFragment())
                            })
                    }
                }

                else -> {
                    Timber.d("Loading")
                }
            }
        }
    }

    private fun initSchedule() {
        viewModel.scheduleResult.observe(viewLifecycleOwner) { response ->
            when (response) {

                is ApiResponse.Loading -> {
                    Timber.d("Loading")
                    initLoading(true)
                }

                is ApiResponse.Success -> {
                    val responseData = response.data.data

                    Timber.tag("SCHEDULE_LENGTH").d(responseData?.size.toString())

                    if (responseData?.size!! > 0) {
                        Timber.tag("SCHEDULE_LENGTH_2").d(responseData.size.toString())
                        scheduleAdapter.setData(responseData as ArrayList<ScheduleModel>)
                        binding.rvSchedules.adapter = scheduleAdapter
                    } else {

                        binding.apply {
                            layoutEmptyItem.emptyView.visibility = View.VISIBLE
                            layoutEmptyItem.tvEmptyMessage.text =
                                "Tidak ada jadwal yang tersedia"
                            rvSchedules.visibility = View.GONE
                        }
                    }

                    initLoading(false)
                }

                is ApiResponse.Error -> {
                    Timber.e(response.errorMessage)
                    viewModel.fetchLocalSchedules()
                }

                else -> {
                    Timber.d("Loading")
                }
            }

        }
    }


}