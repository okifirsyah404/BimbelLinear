package com.okifirsyah.bimbellinear.presentation.view.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.okifirsyah.bimbellinear.data.model.FaqModel
import com.okifirsyah.bimbellinear.databinding.FragmentFaqBinding
import com.okifirsyah.bimbellinear.presentation.adapter.FaqAdapter
import com.okifirsyah.bimbellinear.presentation.base.BaseFragment
import com.okifirsyah.bimbellinear.utils.constant.pageTitleConstant

class FaqFragment : BaseFragment<FragmentFaqBinding>() {

    private val faqAdapter: FaqAdapter by lazy { FaqAdapter() }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFaqBinding {
        return FragmentFaqBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
        binding.rvFaq.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = faqAdapter
        }

        faqAdapter.setData(faqList)
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    override fun initAppBar() {
        binding.toolbar.mainToolbar.apply {
            title = pageTitleConstant.FAQ
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private val faqList = ArrayList<FaqModel>(
        listOf(
            FaqModel(
                "Tagihan",
                "Bagaimana cara melihat riwayat transaksi?",
                "Untuk melihat riwayat tagihan, anda bisa masuk ke halaman akun dan klik menu tagihan, selanjut nya anda akan diarahkan pada halaman tagihan yang berisi riwayat transaksi pembayaran anda",
            ),
            FaqModel(
                "Module",
                "Bagaimana cara download module pembelajaran?",
                "Untuk mendownload module pembelajaran anda bisa masuk ke halaman akun dan klik menu module, anda akan diarahkan pada halaman module, kemudian klik module yang ingin anda download, maka module akan otomatis kedownload.",
            ),
            FaqModel(
                "Informasi kelompok",
                "Bagaimana cara melihat detail informasi kelompok?",
                "Anda bisa melihat detail informasi kelompok dengan mengklik menu informasi kelompok pada halaman akun, kemudian anda akan diarahkan pada halaman informasi kelompok",
            ),
        )
    )

}