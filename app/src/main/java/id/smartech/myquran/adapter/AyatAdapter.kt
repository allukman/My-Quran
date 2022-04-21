package id.smartech.myquran.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.smartech.myquran.R
import id.smartech.myquran.databinding.ItemAyatBinding
import id.smartech.myquran.ui.surah.model.AyahModel
import id.smartech.myquran.ui.surah.model.ListAyatModel
import id.smartech.myquran.util.Helper.fromHtml
import id.smartech.myquran.util.KarsaLogger

class AyatAdapter(private val items: ArrayList<AyahModel>): RecyclerView.Adapter<AyatAdapter.AyatViewHolder>() {

    fun addList(list: List<AyahModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class AyatViewHolder(val bind: ItemAyatBinding): RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        return AyatViewHolder(
            DataBindingUtil.inflate(
                (LayoutInflater.from(parent.context)),
                R.layout.item_ayat,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        val item = items[position]
        val number = item.number!!.inSurah!!

        holder.bind.number.text = item.number?.inSurah.toString()
        holder.bind.ar.text = item.text?.arab
        holder.bind.tr.text = item.text?.transliteration?.en
        holder.bind.idn.text = item.translation?.id

        if (number % 2 == 1) {
            holder.bind.container.setBackgroundColor(Color.parseColor("#eefbf2"))
        } else {
            holder.bind.container.setBackgroundColor(Color.parseColor("#ffffff"))
        }
    }
}