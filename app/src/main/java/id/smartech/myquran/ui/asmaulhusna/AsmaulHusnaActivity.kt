package id.smartech.myquran.ui.asmaulhusna

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.smartech.myquran.R
import id.smartech.myquran.adapter.AsmaulHusnaAdapter
import id.smartech.myquran.base.BaseActivity
import id.smartech.myquran.databinding.ActivityAsmaulHusnaBinding
import id.smartech.myquran.ui.asmaulhusna.model.AsmaulHusnaModel
import id.smartech.myquran.ui.asmaulhusna.model.AsmaulHusnaViewModel

class AsmaulHusnaActivity : BaseActivity<ActivityAsmaulHusnaBinding>() {
    private lateinit var viewModel: AsmaulHusnaViewModel
    private lateinit var mAdapter: AsmaulHusnaAdapter
    private var list = ArrayList<AsmaulHusnaModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setLayout = R.layout.activity_asmaul_husna
        super.onCreate(savedInstanceState)

        setOnClick()
        setViewModel()
        setRecyclerView()
        subscribeLiveData()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this).get(AsmaulHusnaViewModel::class.java)
        viewModel.setService(createGithubApi(this))
        viewModel.getAsmaulHusna()
    }

    private fun setOnClick() {
        bind.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun subscribeLiveData() {
        this.let {
            viewModel.isLoadingLiveData.observe(it) { isLoading ->

            }
        }

        this.let {
            viewModel.onSuccessLiveData.observe(it) { list ->
                mAdapter.addList(list)
            }
        }
    }

    private fun setRecyclerView() {
        bind.rvAsmaulhusna.isNestedScrollingEnabled = false
        mAdapter = AsmaulHusnaAdapter(list)
        bind.rvAsmaulhusna.adapter = mAdapter
        bind.rvAsmaulhusna.layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        bind.rvAsmaulhusna.itemAnimator = DefaultItemAnimator()
    }

}