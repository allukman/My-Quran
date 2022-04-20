package id.smartech.myquran.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
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
import id.smartech.myquran.util.Helper
import id.smartech.myquran.util.KarsaLogger

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var viewModel: MainViewModel
    private lateinit var mAdapter: SurahAdapter
    private var list = ArrayList<ListSurahModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setLayout = R.layout.activity_main
        super.onCreate(savedInstanceState)

        initData()
        setViewModel()
        setRecyclerView()
        subscribeLiveData()
    }

    private fun initData() {
        val date = Helper.getCurrentDate()
        val day = Helper.getCurrentDayOfWeek()
        val today = "$day, $date"

        bind.textClock.format12Hour = "kk:mm"
        bind.date.text = today
    }

    private fun subscribeLiveData() {
        this.let {
            viewModel.isLoadingLiveData.observe(it) { isLoading ->
                if (isLoading) {
                    onLoading()
                } else {
                    showData()
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

    private fun onLoading() {
        bind.shimmerLayout.visibility = View.VISIBLE
        bind.rvSurah.visibility = View.GONE
    }

    private fun showData() {
        bind.shimmerLayout.visibility = View.GONE
        bind.rvSurah.visibility = View.VISIBLE
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.setService(createApi(this))
        viewModel.getListSurah()
    }

    private fun setRecyclerView() {
        bind.rvSurah.isNestedScrollingEnabled = false
        mAdapter = SurahAdapter(list)
        bind.rvSurah.adapter = mAdapter
        bind.rvSurah.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        bind.rvSurah.itemAnimator = DefaultItemAnimator()

        mAdapter.setOnItemClickCallback(object : SurahAdapter.OnItemClickCallback {
            override fun onClickItem(data: ListSurahModel) {
                val intent = Intent(this@MainActivity, DetailSurahActivity::class.java)
                intent.putExtra("surah", data)
                startActivity(intent)

                KarsaLogger.print(Gson().toJson(data))
            }
        })
    }
}