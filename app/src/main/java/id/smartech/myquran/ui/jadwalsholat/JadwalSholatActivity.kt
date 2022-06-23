package id.smartech.myquran.ui.jadwalsholat

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import id.smartech.myquran.R
import id.smartech.myquran.base.BaseActivity
import id.smartech.myquran.databinding.ActivityJadwalSholatBinding
import id.smartech.myquran.ui.jadwalsholat.lokasi.LokasiActivity
import id.smartech.myquran.ui.jadwalsholat.lokasi.LokasiViewModel
import id.smartech.myquran.ui.jadwalsholat.model.JadwalSholatModel
import id.smartech.myquran.util.Helper
import id.smartech.myquran.util.KarsaLogger

class JadwalSholatActivity : BaseActivity<ActivityJadwalSholatBinding>() {
    private lateinit var viewModel: JadwalSholatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setLayout = R.layout.activity_jadwal_sholat
        super.onCreate(savedInstanceState)

        initData()
        setOnClick()
        setViewModel()
        cekLocation()
        subscribeLiveData()

        KarsaLogger.print("location id : " + sharedPref.locationId)
    }

    override fun onResume() {
        super.onResume()
        cekLocation()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this).get(JadwalSholatViewModel::class.java)
        viewModel.setService(createMyQuranApi(this))
    }

    private fun cekLocation() {
        if (sharedPref.locationId.isNullOrEmpty()) {
            intents<LokasiActivity>(this)
            this.finish()
        } else {
            val date = Helper.getCurrentDateAdzan().split("/")
            val location = sharedPref.locationId

            viewModel.getJadwalAdzan(location!!, date[0], date[1], date[2])
        }
    }

    private fun subscribeLiveData() {
        this.let {
            viewModel.isLoadingLiveData.observe(it) { isLoading ->
                if (isLoading) {
                    hideAll()
                } else {
                    showAll()
                }
            }
        }

        this.let {
            viewModel.onSuccessLiveData.observe(it) { data ->
                if (data != null) {
                    setData(data)
                }
            }
        }
    }

    private fun setData(d : JadwalSholatModel) {
        bind.imsak.text = "Imsak : ${d.jadwal!!.imsak}"
        bind.subuh.text = "Subuh : ${d.jadwal!!.subuh}"
        bind.dzuhur.text = "Dzuhur : ${d.jadwal!!.dzuhur}"
        bind.ashar.text = "Ashar : ${d.jadwal!!.ashar}"
        bind.maghrib.text = "Maghrib : ${d.jadwal!!.maghrib}"
        bind.isya.text = "Isya : ${d.jadwal!!.isya}"

        bind.kota.text = d.lokasi
    }

    private fun hideAll() {
        bind.jadwalSholatBulanan.visibility = View.GONE
        bind.containerImsak.visibility = View.GONE
        bind.containerSubuh.visibility = View.GONE
        bind.containerDzuhur.visibility = View.GONE
        bind.containerAshar.visibility = View.GONE
        bind.containerMaghrib.visibility = View.GONE
        bind.containerIsya.visibility = View.GONE
        bind.progressBar.visibility = View.VISIBLE
    }

    private fun showAll() {
        bind.jadwalSholatBulanan.visibility = View.VISIBLE
        bind.containerImsak.visibility = View.VISIBLE
        bind.containerSubuh.visibility = View.VISIBLE
        bind.containerDzuhur.visibility = View.VISIBLE
        bind.containerAshar.visibility = View.VISIBLE
        bind.containerMaghrib.visibility = View.VISIBLE
        bind.containerIsya.visibility = View.VISIBLE
        bind.progressBar.visibility = View.GONE
    }


    private fun setOnClick() {
        bind.location.setOnClickListener {
            intents<LokasiActivity>(this)
        }

        bind.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initData() {
        val date = Helper.getCurrentDate()
        val day = Helper.getCurrentDayOfWeek()
        val today = "$day, $date"

        bind.date.text = today
    }
}