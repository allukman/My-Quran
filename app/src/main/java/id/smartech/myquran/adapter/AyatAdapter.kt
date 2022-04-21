package id.smartech.myquran.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.smartech.myquran.R
import id.smartech.myquran.databinding.ItemAyatBinding
import id.smartech.myquran.ui.surah.model.ListAyatModel
import id.smartech.myquran.util.Helper.fromHtml

class AyatAdapter(private val items: ArrayList<ListAyatModel>): RecyclerView.Adapter<AyatAdapter.AyatViewHolder>() {

    fun addList(list: List<ListAyatModel>) {
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

        holder.bind.number.text = item.nomor.toString()
        holder.bind.ar.text = item.ar
        holder.bind.tr.text = item.tr?.fromHtml()
        holder.bind.idn.text = item.idn
    }
}