package com.okifirsyah.bimbellinear.presentation.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.DialogFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import com.okifirsyah.bimbellinear.databinding.DialogCameraOrGaleryBinding

class CameraOrGalleryDialog(private val launcher: ActivityResultLauncher<Intent>) :
    DialogFragment() {
    private var _binding: DialogCameraOrGaleryBinding? = null
    val binding get() = _binding!!

    companion object {
        const val TAG = "ImagePickerDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCameraOrGaleryBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTileCamera.setOnClickListener {
            initCamera()
            dismiss()
        }

        binding.btnTileGallery.setOnClickListener {
            initGallery()
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initCamera() {
        ImagePicker.with(this)
            .compress(1024)
            .maxResultSize(1080, 1080)
            .cameraOnly()
            .crop()
            .createIntent { intent ->
                launcher.launch(intent)
            }
    }

    private fun initGallery() {
        ImagePicker.with(this)
            .compress(1024)
            .maxResultSize(1080, 1080)
            .galleryOnly()
            .crop()
            .galleryMimeTypes(
                arrayOf(
                    "image/png",
                    "image/jpg",
                    "image/jpeg"
                )
            )
            .createIntent { intent ->
                launcher.launch(intent)
            }
    }


}