package id.smartech.myquran.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.smartech.myquran.R
import id.smartech.myquran.databinding.ItemLocationBinding
import id.smartech.myquran.ui.jadwalsholat.lokasi.model.LocationModel
import id.smartech.myquran.ui.kisahnabi.model.KisahNabiModel

class LocationAdapter(private val items: ArrayList<LocationModel>): RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun addList(list: List<LocationModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class LocationViewHolder(val bind: ItemLocationBinding): RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            DataBindingUtil.inflate(
                (LayoutInflater.from(parent.context)),
                R.layout.item_location,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = items[position]

        holder.bind.name.text = item.lokasi

        holder.itemView.setOnClickListener {
            onItemClickCallback.onClickItem(item)
        }

    }

    interface OnItemClickCallback {
        fun onClickItem(data: LocationModel)
    }


}