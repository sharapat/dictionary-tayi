package uz.tayi.lugat.ui.dictionary.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.tayi.lugat.R
import uz.tayi.lugat.data.local.LugatEntity

class DictionaryAdapter(private val listener: DictionaryItemClickListener) : RecyclerView.Adapter<DictionaryItemViewHolder>() {

    var models: List<LugatEntity> = arrayListOf()

    fun setData(models: List<LugatEntity>) {
        this.models = models
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dictionary, parent, false)
        return DictionaryItemViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: DictionaryItemViewHolder, position: Int) {
        holder.populateModel(models[position], listener)
    }
}