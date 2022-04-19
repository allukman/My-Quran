package id.smartech.myquran.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.smartech.myquran.R
import id.smartech.myquran.databinding.ItemSurahBinding
import id.smartech.myquran.ui.main.model.ListSurahModel

class SurahAdapter(private val items: ArrayList<ListSurahModel>): RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun addList(list: List<ListSurahModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
    class SurahViewHolder(val bind: ItemSurahBinding): RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        return SurahViewHolder(
            DataBindingUtil.inflate(
                (LayoutInflater.from(parent.context)),
                R.layout.item_surah,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val item = items[position]
        val detail = "${item.tempatTurun} - ${item.jumlahAyat} ayat"

        holder.bind.number.text = item.nomor.toString()
        holder.bind.surahNameLatin.text = item.namaLatin
        holder.bind.surahName.text = item.nama
        holder.bind.detail.text = detail

        holder.itemView.setOnClickListener {
            onItemClickCallback.onClickItem(item)
        }
    }

    interface OnItemClickCallback {
        fun onClickItem(data: ListSurahModel)
    }
}