package id.smartech.myquran.ui.surah

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import id.smartech.myquran.R
import id.smartech.myquran.adapter.AyatAdapter
import id.smartech.myquran.base.BaseActivity
import id.smartech.myquran.databinding.ActivityDetailSurahBinding
import id.smartech.myquran.ui.main.model.SurahModel
import id.smartech.myquran.ui.surah.model.AyahModel
import id.smartech.myquran.ui.surah.model.ListAyatModel
import id.smartech.myquran.util.KarsaLogger

class DetailSurahActivity : BaseActivity<ActivityDetailSurahBinding>() {
    lateinit var viewModel: DetailSurahViewModel
    private lateinit var mAdapter: AyatAdapter
    private var list = ArrayList<AyahModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setLayout = R.layout.activity_detail_surah
        super.onCreate(savedInstanceState)

        val data = intent.getSerializableExtra("surah") as SurahModel

        initData(data)
        toggleBookmark()
        setOnClick()
        setViewModel(data.number.toString())
        setRecyclerView()
        subscribeLiveData()

    }

    private fun setViewModel(id: String) {
        viewModel = ViewModelProvider(this).get(DetailSurahViewModel::class.java)
        viewModel.setService(createApi(this))
        viewModel.getDetailSurah(id)
    }

    private fun subscribeLiveData() {
        this.let {
            viewModel.isLoadingLiveData.observe(it) { isLoading ->
                if (isLoading) {
                    bind.rvAyat.visibility = View.GONE
                    bind.shimmerLayout.visibility = View.VISIBLE
                } else {
                    bind.rvAyat.visibility = View.VISIBLE
                    bind.shimmerLayout.visibility = View.GONE
                }
            }
        }

        this.let {
            viewModel.onSuccessLiveData.observe(it) { data ->
                mAdapter.addList(data.verses)
                KarsaLogger.print("detail surah " + Gson().toJson(data))
            }
        }
    }

    private fun setOnClick() {
        bind.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setRecyclerView() {
        bind.rvAyat.isNestedScrollingEnabled = false
        mAdapter = AyatAdapter(list)
        bind.rvAyat.adapter = mAdapter
        bind.rvAyat.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        bind.rvAyat.itemAnimator = DefaultItemAnimator()

    }

    private fun toggleBookmark() {
        bind.bookmark.setOnClickListener {
            bind.bookmark.visibility = View.GONE
            bind.unbookmark.visibility = View.VISIBLE
        }

        bind.unbookmark.setOnClickListener {
            bind.unbookmark.visibility = View.GONE
            bind.bookmark.visibility = View.VISIBLE
        }
    }

    private fun initData(d: SurahModel) {
        bind.surahNameLatin.text = d.name?.transliteration?.id
        bind.arti.text = d.name?.translation?.id
        bind.detail.text = "${d.revelation?.id} - ${d.numberOfVerses} ayat"
        bind.title.text = d.name?.transliteration?.id
    }
}