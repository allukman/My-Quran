package id.smartech.myquran.ui.kisahnabi

import android.os.Bundle
import id.smartech.myquran.R
import id.smartech.myquran.base.BaseActivity
import id.smartech.myquran.databinding.ActivityKisahNabiDetailBinding
import id.smartech.myquran.ui.kisahnabi.model.KisahNabiModel

class KisahNabiDetailActivity : BaseActivity<ActivityKisahNabiDetailBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setLayout = R.layout.activity_kisah_nabi_detail
        super.onCreate(savedInstanceState)

        val data = intent.getSerializableExtra("data") as KisahNabiModel
        initData(data)
    }

    private fun initData(d: KisahNabiModel) {
        bind.title.text = d.name
        bind.tempat.text = "Tempat di turunkan : " + d.tmp
        bind.desc.text = d.desc
    }
}