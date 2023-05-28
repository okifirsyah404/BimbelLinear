package com.okifirsyah.bimbellinear.presentation.view.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import com.okifirsyah.bimbellinear.data.model.UserModel
import com.okifirsyah.bimbellinear.data.network.ApiResponse
import com.okifirsyah.bimbellinear.databinding.FragmentProfileBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.constant.stateKeyConstant
import com.okifirsyah.bimbellinear.utils.extensions.intentToPackageSettings
import com.okifirsyah.bimbellinear.utils.extensions.parsePhoneNumber
import com.okifirsyah.bimbellinear.utils.extensions.showCameraOrGalleryDialog
import com.okifirsyah.bimbellinear.utils.extensions.showCustomDialog
import com.okifirsyah.bimbellinear.utils.extensions.showHttpErrorDialog
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
        binding.toolbar.mainToolbar.apply {
            title = pageTitleConstant.PROFILE
            navigationIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_close)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun initUI() {
        initUserProfile()
        binding.tvAppVersion.text = "v${BuildConfig.VERSION_NAME}"
    }

    override fun initProcess() {
        viewModel.getAuthToken()
        viewModel.contactSupport()
    }

    override fun initObservers() {
        initTheme()
        initContactSupport()
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
        binding.apply {
            btnTileModule.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToModuleBookFragment())
            }
            btnTileLogout.setOnClickListener {
                logout()
            }
            btnTileChangePassword.setOnClickListener {
                navigateToChangePassword()
            }
            btnTileGroupInfo.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToGroupInfoFragment())
            }
            btnTileBill.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToBillListFragment())
            }
            civPersonAvatar.setOnClickListener {
                showCameraOrGalleryDialog(launcher)
            }
            btnTileFaq.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFaqFragment())
            }
            btnTileAboutApp.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToAboutAppFragment())
            }
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
            .load("${BuildConfig.BASE_URL}/img?img=${args.userProfileArgs?.id}.jpg")
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .circleCrop()
            .into(binding.civPersonAvatar)
    }

    private fun navigateToChangePassword() {
        if (args.userProfileArgs != null) {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToOtpFragment(
                    args.userProfileArgs
                )
            )
        }
    }

    private fun logout() {
        viewModel.run {
            logout()
            logoutData.observe(viewLifecycleOwner) { response ->

                when (response) {
                    is ApiResponse.Loading -> {
                        Timber.tag("LOGOUT").d("logout: Loading")
                    }

                    is ApiResponse.Success -> {
                        viewModel.setAuthToken("")
                        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSignInFragment())
                    }

                    is ApiResponse.Error -> {
                        Timber.tag("LOGOUT").d("logout: Error")
                        showHttpErrorDialog(response.errorMessage)
                    }

                    else -> {
                        Timber.tag("LOGOUT").d("logout: Else")
                    }
                }


            }
        }
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

    private fun initContactSupport() {
        viewModel.contactSupportData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ApiResponse.Loading -> {
                    Timber.tag("CONTACT_SUPPORT").d("initContactSupport: Loading")
                }

                is ApiResponse.Success -> {
                    Timber.tag("CONTACT_SUPPORT").d("initContactSupport: Success")
                    val contactSupport = response.data.data

                    binding.btnTileContactSupport.setOnClickListener {
                        if (args.userProfileArgs != null && contactSupport != null) intentToWhatsApp(
                            args.userProfileArgs!!,
                            contactSupport.name!!,
                            contactSupport.phoneNumber!!
                        )
                    }

                }

                is ApiResponse.Error -> {
                    Timber.tag("CONTACT_SUPPORT").d("initContactSupport: Error")
                    showHttpErrorDialog(response.errorMessage)
                }

                else -> {
                    Timber.tag("CONTACT_SUPPORT").d("initContactSupport: Else")
                }
            }
        }
    }

    private fun intentToWhatsApp(senderInfo: UserModel, receiverName: String, localNumber: String) {

        val messageTemplate =
            """
                Halo kak $receiverName, saya ${senderInfo.name} dari ${senderInfo.group} ingin bertanya.
                
            """.trimIndent()
        val internationalNumber = localNumber.parsePhoneNumber()

        val url = "whatsapp://send?phone=$internationalNumber&text=$messageTemplate"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}