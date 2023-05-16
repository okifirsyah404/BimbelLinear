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
import com.github.dhaval2404.imagepicker.ImagePicker
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.FragmentProfileBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.constant.stateKeyConstant
import com.okifirsyah.bimbellinear.utils.extensions.showCameraOrGalleryDialog
import com.okifirsyah.bimbellinear.utils.extensions.showCustomDialog
import com.okifirsyah.bimbellinear.utils.helper.renameFile
import org.koin.android.ext.android.inject
import timber.log.Timber

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val viewModel: ProfileViewModel by inject()
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
    }

    override fun initProcess() {
    }

    override fun initObservers() {
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val scrollView = view?.findViewById<ScrollView>(R.id.sv_profile)
        val position: IntArray = intArrayOf(scrollView?.scrollX ?: 0, scrollView?.scrollY ?: 0)

        if (position.isNotEmpty()) {
            outState.putIntArray(
                stateKeyConstant.profileScrollPosition,
                position
            )
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val position = savedInstanceState?.getIntArray(stateKeyConstant.profileScrollPosition)
        if (position != null) {
            binding.svProfile.post { binding.svProfile.scrollTo(position[0], position[1]) }
        }
    }

    override fun initIntent() {
        binding.btnTileModule.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_moduleBookFragment)
        }
        binding.btnTileChangePassword.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_otpFragment)
        }
        binding.btnTileLogout.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_signInFragment)
        }
        binding.btnTileGroupInfo.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_groupInfoFragment)
        }
        binding.btnTileAttendance.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_attendanceHistoryFragment)
        }
        binding.btnTileBill.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_billListFragment)
        }
        binding.civPersonAvatar.setOnClickListener {
            showCameraOrGalleryDialog(launcher)
        }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
                    val fileUri = data?.data

                    fileUri?.let { uri ->
                        Timber.tag("URI_LOC").d("onActivityResult: " + uri)
                        val renamedFile = renameFile(fileUri.path!!, "img_avatar")

                        Timber.tag("URI_LOC").d("onActivityResult: " + renamedFile)

//                        Glide.with(this).

                    }


                }

                ImagePicker.RESULT_ERROR -> {
                    showCustomDialog(
                        "Terjadi Kesalahan",
                        "Terjadi Kesalahan saat mengambil gambar",
                    )
                }

                else -> {
                    return@registerForActivityResult
                }
            }
        }


}