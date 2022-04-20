package id.smartech.myquran.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.smartech.myquran.R
import id.smartech.myquran.databinding.ItemAsmaulHusnaBinding
import id.smartech.myquran.ui.asmaulhusna.model.AsmaulHusnaModel
import id.smartech.myquran.ui.surah.model.ListAyatModel

class AsmaulHusnaAdapter(private val items: ArrayList<AsmaulHusnaModel>): RecyclerView.Adapter<AsmaulHusnaAdapter.AsmaulHusnaViewHolder>() {

    fun addList(list: List<AsmaulHusnaModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class AsmaulHusnaViewHolder(val bind: ItemAsmaulHusnaBinding): RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsmaulHusnaViewHolder {
        return AsmaulHusnaViewHolder(
            DataBindingUtil.inflate(
                (LayoutInflater.from(parent.context)),
                R.layout.item_asmaul_husna,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AsmaulHusnaViewHolder, position: Int) {
        val item = items[position]

        holder.bind.arab.text = item.arab
        holder.bind.text.text = item.latin
        holder.bind.arti.text = item.arti
    }


}