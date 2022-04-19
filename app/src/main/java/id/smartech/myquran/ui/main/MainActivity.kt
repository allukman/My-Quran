package id.smartech.myquran.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import id.smartech.myquran.R
import id.smartech.myquran.adapter.SurahAdapter
import id.smartech.myquran.base.BaseActivity
import id.smartech.myquran.databinding.ActivityMainBinding
import id.smartech.myquran.ui.main.model.ListSurahModel
import id.smartech.myquran.ui.surah.DetailSurahActivity
import id.smartech.myquran.util.KarsaLogger

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var viewModel: MainViewModel
    private lateinit var mAdapter: SurahAdapter
    private var list = ArrayList<ListSurahModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setLayout = R.layout.activity_main
        super.onCreate(savedInstanceState)

        setViewModel()
        setRecyclerView()
        subscribeLiveData()
    }

    private fun subscribeLiveData() {
        this.let {
            viewModel.isLoadingLiveData.observe(it) { isLoading ->

            }
        }

        this.let {
            viewModel.onSuccessLiveData.observe(it) { list ->
                mAdapter.addList(list)
                KarsaLogger.print(Gson().toJson(list))
            }
        }
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.setService(createApi(this))
        viewModel.getListSurah()
    }

    private fun setRecyclerView() {
        mAdapter = SurahAdapter(list)
        bind.rvSurah.adapter = mAdapter
        bind.rvSurah.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        bind.rvSurah.itemAnimator = DefaultItemAnimator()

        mAdapter.setOnItemClickCallback(object : SurahAdapter.OnItemClickCallback {
            override fun onClickItem(data: ListSurahModel) {
                intents<DetailSurahActivity>(this@MainActivity)
            }

        })
    }
}