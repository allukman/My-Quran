package id.smartech.myquran.ui.kisahnabi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import id.smartech.myquran.R
import id.smartech.myquran.adapter.AsmaulHusnaAdapter
import id.smartech.myquran.adapter.KisahNabiAdapter
import id.smartech.myquran.base.BaseActivity
import id.smartech.myquran.databinding.ActivityKisahNabiBinding
import id.smartech.myquran.ui.asmaulhusna.model.AsmaulHusnaModel
import id.smartech.myquran.ui.kisahnabi.model.KisahNabiModel
import id.smartech.myquran.ui.surah.DetailSurahActivity
import id.smartech.myquran.util.KarsaLogger

class KisahNabiActivity : BaseActivity<ActivityKisahNabiBinding>() {
    private lateinit var viewModel: KisahNabiViewModel
    private lateinit var mAdapter: KisahNabiAdapter
    private var list = ArrayList<KisahNabiModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setLayout = R.layout.activity_kisah_nabi
        super.onCreate(savedInstanceState)

        setOnClick()
        setViewModel()
        setRecyclerView()
        subscribeLiveData()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this).get(KisahNabiViewModel::class.java)
        viewModel.setService(createZhirrApi(this))
        viewModel.getKisahNabi()
    }

    private fun subscribeLiveData() {
        this.let {
            viewModel.isLoadingLiveData.observe(it) { isLoading ->
                if (isLoading) {
                    bind.shimmerLayout.visibility = View.VISIBLE
                    bind.rvKisahnabi.visibility = View.GONE
                } else {
                    bind.shimmerLayout.visibility = View.GONE
                    bind.rvKisahnabi.visibility = View.VISIBLE
                }
            }
        }

        this.let {
            viewModel.onSuccessLiveData.observe(it) { list ->
                mAdapter.addList(list)
                KarsaLogger.print(Gson().toJson(list))
            }
        }
    }

    private fun setRecyclerView() {
        bind.rvKisahnabi.isNestedScrollingEnabled = false
        mAdapter = KisahNabiAdapter(list)
        bind.rvKisahnabi.adapter = mAdapter
        bind.rvKisahnabi.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        bind.rvKisahnabi.itemAnimator = DefaultItemAnimator()

        mAdapter.setOnItemClickCallback(object : KisahNabiAdapter.OnItemClickCallback {
            override fun onClickItem(data: KisahNabiModel) {
                val intent = Intent(this@KisahNabiActivity, KisahNabiDetailActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }

        })
    }

    private fun setOnClick() {
        bind.back.setOnClickListener {
            onBackPressed()
        }
    }
}