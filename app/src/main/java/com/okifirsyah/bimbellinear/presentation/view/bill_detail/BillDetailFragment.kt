package com.okifirsyah.bimbellinear.presentation.view.bill_detail

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.okifirsyah.bimbellinear.databinding.FragmentBillDetailBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.extensions.showCameraOrGalleryDialog
import com.okifirsyah.bimbellinear.utils.extensions.showCustomDialog

class BillDetailFragment : BaseFragment<FragmentBillDetailBinding>() {

    private var fileUri: Uri? = null
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBillDetailBinding {
        return FragmentBillDetailBinding.inflate(inflater, container, false)
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.title = pageTitleConstant.BILL_DETAIL
        binding.toolbar.mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initUI() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    override fun initIntent() {
        binding.btnDropZone.setOnClickListener {
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
                    fileUri = data?.data

                    if (fileUri != null) {
                        binding.ivPlaceholder.visibility = View.GONE

                        Glide.with(requireContext())
                            .load(fileUri)
                            .into(binding.ivPreview)

                        binding.ivPreview.visibility = View.VISIBLE
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