package com.okifirsyah.bimbellinear.presentation.view.profile

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ScrollView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.dhaval2404.imagepicker.ImagePicker
import com.okifirsyah.bimbellinear.BuildConfig
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.FragmentProfileBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.constant.stateKeyConstant
import com.okifirsyah.bimbellinear.utils.extensions.intentToPackageSettings
import com.okifirsyah.bimbellinear.utils.extensions.showCameraOrGalleryDialog
import com.okifirsyah.bimbellinear.utils.extensions.showCustomDialog
import com.okifirsyah.bimbellinear.utils.helper.renameFile
import org.koin.android.ext.android.inject
import timber.log.Timber

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val viewModel: ProfileViewModel by inject()

    private val args: ProfileFragmentArgs by navArgs()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.title = pageTitleConstant.PROFILE
        binding.toolbar.mainToolbar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_close)
        binding.toolbar.mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initUI() {
        initUserProfile()
    }

    override fun initProcess() {
        viewModel.getAuthToken()
    }

    override fun initObservers() {
        initTheme()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val scrollView = view?.findViewById<ScrollView>(R.id.sv_profile)
        val position: IntArray = intArrayOf(scrollView?.scrollX ?: 0, scrollView?.scrollY ?: 0)

        if (position.isNotEmpty()) {
            outState.putIntArray(
                stateKeyConstant.PROFILE_SCROLL_POSITION,
                position
            )
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val position = savedInstanceState?.getIntArray(stateKeyConstant.PROFILE_SCROLL_POSITION)
        if (position != null) {
            binding.svProfile.post { binding.svProfile.scrollTo(position[0], position[1]) }
        }
    }

    override fun initIntent() {
        binding.btnTileModule.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToModuleBookFragment())
        }
        binding.btnTileChangePassword.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToOtpFragment())
        }
        binding.btnTileLogout.setOnClickListener {
            logout()
        }
        binding.btnTileGroupInfo.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToGroupInfoFragment())
        }
        binding.btnTileBill.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToBillListFragment())
        }
        binding.civPersonAvatar.setOnClickListener {
            showCameraOrGalleryDialog(launcher)
        }
    }

    private fun initTheme() {
        viewModel.getThemeSettings().observe(
            this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        }

        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            viewModel.saveThemeSetting(isChecked)
        }
    }

    private fun initUserProfile() {
        initUserAvatar()
        val userProfile = args.userProfileArgs

        if (userProfile != null) {
            binding.apply {
                tvPersonName.text = userProfile.name
                tvProfileGroup.text = userProfile.group
                tvGroupType.text = userProfile.groupType
            }
        }
    }

    private fun initUserAvatar() {
        Glide.with(this)
            .load("${BuildConfig.BASE_IMAGE_URL}${args.userProfileArgs?.id}.jpg")
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .circleCrop()
            .into(binding.civPersonAvatar)
    }

    private fun logout() {
        viewModel.setAuthToken("")
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSignInFragment())
    }


    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    val fileUri = data?.data

                    fileUri?.let { uri ->
                        Timber.tag("URI_LOC").d("onActivityResult: " + uri)
                        val renamedFile = renameFile(fileUri.path!!, "img_avatar")

                        viewModel.userToken.observe(viewLifecycleOwner) { token ->
                            viewModel.uploadAvatar(
                                requireContext(),
                                viewLifecycleOwner,
                                token,
                                renamedFile!!,
                            )
                        }

                        Glide.with(this)
                            .load(renamedFile)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .circleCrop()
                            .into(binding.civPersonAvatar)
                    }

                }

                ImagePicker.RESULT_ERROR -> {
                    showCustomDialog(
                        "Izin Diperlukan",
                        "Silahkan berikan izin untuk mengakses kamera dan penyimpanan",
                        submitText = "Buka Pengaturan",
                        onSubmit = {
                            intentToPackageSettings()
                        }
                    )
                }

                else -> {
                    return@registerForActivityResult
                }
            }
        }
}