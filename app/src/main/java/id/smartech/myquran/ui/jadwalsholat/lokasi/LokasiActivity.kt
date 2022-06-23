package id.smartech.myquran.ui.jadwalsholat.lokasi

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import id.smartech.myquran.R
import id.smartech.myquran.adapter.LocationAdapter
import id.smartech.myquran.base.BaseActivity
import id.smartech.myquran.databinding.ActivityLokasiBinding
import id.smartech.myquran.ui.jadwalsholat.JadwalSholatActivity
import id.smartech.myquran.ui.jadwalsholat.lokasi.model.LocationModel
import id.smartech.myquran.util.KarsaLogger

class LokasiActivity : BaseActivity<ActivityLokasiBinding>() {
    private lateinit var viewModel: LokasiViewModel
    private lateinit var mAdapter: LocationAdapter
    private var list = ArrayList<LocationModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setLayout = R.layout.activity_lokasi
        super.onCreate(savedInstanceState)

        setViewModel()
        setRecyclerView()
        subscribeLiveData()
        setSearchView()
        setOnClick()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this).get(LokasiViewModel::class.java)
        viewModel.setService(createMyQuranApi(this))
    }

    private fun setOnClick() {
        bind.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setSearchView() {
        bind.searchButton.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getLocation(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun subscribeLiveData() {
        this.let {
            viewModel.isLoadingLiveData.observe(it) { isLoading ->
                if (isLoading) {
                    bind.shimmerLayout.visibility = View.VISIBLE
                    bind.rvLocation.visibility = View.GONE
                } else {
                    bind.shimmerLayout.visibility = View.GONE
                }
            }
        }

        this.let {
            viewModel.onSuccessLiveData.observe(it) { list ->
                if (list.isNullOrEmpty()) {
                    noticeToast("Kota Tidak Ketemu")
                    bind.rvLocation.visibility = View.GONE
                } else {
                    mAdapter.addList(list)
                    bind.rvLocation.visibility = View.VISIBLE
                }

            }
        }
    }

    private fun setRecyclerView() {
        bind.rvLocation.isNestedScrollingEnabled = false
        mAdapter = LocationAdapter(list)
        bind.rvLocation.adapter = mAdapter
        bind.rvLocation.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        bind.rvLocation.itemAnimator = DefaultItemAnimator()

        mAdapter.setOnItemClickCallback(object : LocationAdapter.OnItemClickCallback {
            override fun onClickItem(data: LocationModel) {
                sharedPref.saveLocationId(data.id.toString())
                intents<JadwalSholatActivity>(this@LokasiActivity)
                this@LokasiActivity.finish()
            }
        })
    }
}