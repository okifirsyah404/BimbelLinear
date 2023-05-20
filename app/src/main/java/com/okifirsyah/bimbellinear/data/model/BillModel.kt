package com.okifirsyah.bimbellinear.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BillModel(
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("bulan")
    val month: String? = null,
    @field:SerializedName("tahun")
    val year: Int? = null,
    @field:SerializedName("jumlah")
    val amount: Int? = null,
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("nama_rekening")
    val holderName: String? = null,
    @field:SerializedName("nomor_rekening")
    val holderNumber: String? = null,
    @field:SerializedName("tanggal_bayar")
    val paymentDate: String? = null,
    @field:SerializedName("jatuh_tempo")
    val dueDate: String? = null,
    @field:SerializedName("tanggal_approve")
    val approvedDate: String? = null,
) : Parcelable
