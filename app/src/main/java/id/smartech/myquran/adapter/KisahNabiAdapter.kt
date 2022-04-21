package id.smartech.myquran.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.smartech.myquran.R
import id.smartech.myquran.databinding.ActivityKisahNabiBinding
import id.smartech.myquran.databinding.ItemKisahNabiBinding
import id.smartech.myquran.ui.kisahnabi.KisahNabiViewModel
import id.smartech.myquran.ui.kisahnabi.model.KisahNabiModel
import id.smartech.myquran.ui.main.model.SurahModel
import id.smartech.myquran.ui.surah.model.AyahModel

class KisahNabiAdapter(private val items: ArrayList<KisahNabiModel>): RecyclerView.Adapter<KisahNabiAdapter.KisahNabiViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun addList(list: List<KisahNabiModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class KisahNabiViewHolder(val bind: ItemKisahNabiBinding): RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KisahNabiViewHolder {
        return KisahNabiViewHolder(
            DataBindingUtil.inflate(
                (LayoutInflater.from(parent.context)),
                R.layout.item_kisah_nabi,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: KisahNabiViewHolder, position: Int) {
        val item = items[position]

        holder.bind.name.text = item.name

        holder.itemView.setOnClickListener {
            onItemClickCallback.onClickItem(item)
        }
    }

    interface OnItemClickCallback {
        fun onClickItem(data: KisahNabiModel)
    }


}