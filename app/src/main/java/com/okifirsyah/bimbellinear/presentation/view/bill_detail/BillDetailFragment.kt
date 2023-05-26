package com.okifirsyah.bimbellinear.presentation.view.bill_detail

import android.app.Activity
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.paris.extensions.style
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.okifirsyah.bimbellinear.BuildConfig
import com.okifirsyah.bimbellinear.R
import com.okifirsyah.bimbellinear.databinding.FragmentBillDetailBinding
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.presentation.dialog.SingleButtonDialog
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant
import com.okifirsyah.bimbellinear.utils.enums.BillStatusEnum
import com.okifirsyah.bimbellinear.utils.extensions.enumValueToTitleCase
import com.okifirsyah.bimbellinear.utils.extensions.getFormattedDate
import com.okifirsyah.bimbellinear.utils.extensions.intentToPackageSettings
import com.okifirsyah.bimbellinear.utils.extensions.showCameraOrGalleryDialog
import com.okifirsyah.bimbellinear.utils.extensions.showCustomDialog
import com.okifirsyah.bimbellinear.utils.extensions.titleCaseToEnumValue
import com.okifirsyah.bimbellinear.utils.extensions.toDate
import com.okifirsyah.bimbellinear.utils.extensions.toRupiah
import com.okifirsyah.bimbellinear.utils.extensions.toTitleCase
import com.okifirsyah.bimbellinear.utils.helper.renameFile
import jp.wasabeef.glide.transformations.BlurTransformation
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.io.File

class BillDetailFragment : BaseFragment<FragmentBillDetailBinding>() {

    private val viewModel: BillDetailViewModel by inject()
    private val args: BillDetailFragmentArgs by navArgs()

    private var billFile: File? = null
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
        initBillDetail()
    }

    override fun initProcess() {
        viewModel.getAuthToken()
    }

    override fun initObservers() {
    }

    override fun initIntent() {
        binding.btnDropZone.setOnClickListener {
            showCameraOrGalleryDialog(launcher)
        }
    }

    private fun initBillDetail() {
        if (args.billArgs != null) {
            val status = args.billArgs?.status?.toTitleCase()?.titleCaseToEnumValue()

            binding.apply {
                tvPeriod.text =
                    "Tagihan Bimbel Bulan ${args.billArgs?.month?.toTitleCase()} ${args.billArgs?.year}"
                tvDueDate.text = args.billArgs?.dueDate?.toDate()?.getFormattedDate()
                tvPaymentDate.text =
                    args.billArgs?.paymentDate?.toDate()?.getFormattedDate() ?: "Belum Dibayar"
                tvStatus.text = args.billArgs?.status?.enumValueToTitleCase()
                tvTotal.text = args.billArgs?.amount?.toRupiah()

                tvApprovedDate.text = when {
                    args.billArgs?.approvedDate != null -> {
                        args.billArgs?.approvedDate?.toDate()?.getFormattedDate()
                    }

                    args.billArgs?.paymentDate != null -> {
                        "Menunggu Konfirmasi"
                    }

                    else -> {
                        "Belum Dibayar"
                    }
                }
            }

            initHistoryData(status)
            initStatusColor(status!!)
        }
    }

    private fun initHistoryData(status: String?) {
        if (status != BillStatusEnum.LUNAS.name && status != BillStatusEnum.PENDING.name) {
            binding.apply {
                llTransfersInfo.visibility = View.VISIBLE
                tvBankName.text = args.billArgs?.holderName
                tvAccountNumber.apply {
                    text = args.billArgs?.holderNumber
                    setOnClickListener {
                        copyToClipboard(args.billArgs?.holderNumber!!)
                    }
                }

                btnUpload.setOnClickListener {
                    uploadBill()
                }
            }
        } else {
            binding.apply {
                llTransfersInfo.visibility = View.GONE
                setDisableButton()
                fetchBillImage()
            }
        }
    }

    private fun initStatusColor(status: String) {
        Timber.tag("COLOR").d("initStatusColor: " + status)
        if (status != BillStatusEnum.BELUM_BAYAR.name) binding.apply {
            val color = requireContext().getColor(getBillStatusColor(status))
            tvStatus.setTextColor(color)
            tvTotal.setTextColor(color)
            tvApprovedDate.setTextColor(color)
        }
    }

    private fun getBillStatusColor(status: String): Int {
        return when (status) {
            BillStatusEnum.JATUH_TEMPO.name -> R.color.error_50
            BillStatusEnum.LUNAS.name -> R.color.primary_40
            BillStatusEnum.PENDING.name -> R.color.secondary_50
            else -> R.color.neutral_0
        }
    }

    private fun setDisableButton() {
        binding.apply {
            btnUpload.apply {
                style(R.style.FilledButtonTheme_Disable)
                setOnClickListener(null)
            }

            ivPlaceholder.setOnClickListener(null)
            ivPreview.setOnClickListener(null)
        }
    }

    private fun fetchBillImage() {

        binding.apply {
            ivPlaceholder.visibility = View.GONE
            ivPreview.visibility = View.VISIBLE
            ivPreviewBackground.visibility = View.VISIBLE

            ivPlaceholder.setOnClickListener(null)
            ivPreview.setOnClickListener(null)
        }

        val glideUrl = GlideUrl(
            "${BuildConfig.BASE_IMAGE_URL}/tagihan/${args.billArgs?.id}.jpg",
            LazyHeaders.Builder()
                .addHeader("Authorization", "")
                .build()
        )

        Glide.with(this)
            .load(glideUrl)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.ivPreview)

        Glide.with(this)
            .load(glideUrl)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(16)))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.ivPreviewBackground)

    }

    private fun uploadBill() {
        viewModel.userToken.observe(viewLifecycleOwner) { token ->
            if (billFile != null) {
                viewModel.uploadBIll(
                    requireContext(),
                    viewLifecycleOwner,
                    token,
                    billFile!!,
                    args.billArgs?.id ?: "",
                    onError = {
                        showCustomDialog(
                            "Gagal mengunggah bukti pembayaran",
                            "Silahkan coba lagi",
                            dialogType = SingleButtonDialog.FAILED_DIALOG
                        )
                    },
                    onSuccess = {
                        showCustomDialog(
                            "Berhasil mengunggah bukti pembayaran",
                            "Silahkan tunggu konfirmasi dari admin",
                            dialogType = SingleButtonDialog.SUCCESS_DIALOG,
                            submitText = "Kembali",
                            onSubmit = {
                                backPreviousStack()
                            }
                        )
                    }
                )
            } else {
                showCustomDialog(
                    "Tidak ada gambar yang dipilih",
                    "Silahkan pilih gambar terlebih dahulu",
                    dialogType = SingleButtonDialog.FAILED_DIALOG
                )
            }
        }
    }

    private fun copyToClipboard(text: String) {
        val clipboardManager =
            requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = android.content.ClipData.newPlainText("Text", text)
        clipboardManager.setPrimaryClip(clipData)

        Toast.makeText(requireContext(), "Number copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    private fun backPreviousStack() {
        findNavController().popBackStack()
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
                        val renamedFile = renameFile(fileUri.path!!, "img_bill")
                        billFile = renamedFile

                        Glide.with(this)
                            .load(renamedFile)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.ivPreview)

                        Glide.with(this)
                            .load(renamedFile)
                            .apply(RequestOptions.bitmapTransform(BlurTransformation(16)))
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.ivPreviewBackground)

                        binding.apply {
                            ivPlaceholder.visibility = View.GONE
                            ivPreview.visibility = View.VISIBLE
                            ivPreviewBackground.visibility = View.VISIBLE
                        }

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