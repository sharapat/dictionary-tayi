package uz.tayi.lugat.ui.history.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.tayi.lugat.R
import uz.tayi.lugat.data.local.LugatEntity

class HistoryAdapter(private val listener: OnHistoryItemClickListener) : RecyclerView.Adapter<HistoryItemViewHolder>() {

    private var models: List<LugatEntity> = arrayListOf()

    fun setData(models: List<LugatEntity>) {
        this.models = models
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        return HistoryItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_history_list, parent, false), listener)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        holder.populateModel(models[position])
    }
}