package uz.tayi.lugat.ui.dictionary.suggestion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.tayi.lugat.R
import uz.tayi.lugat.data.local.LugatEntity

class SuggestionAdapter(private val listener: SuggestionItemClickListener) : RecyclerView.Adapter<SuggestionItemViewHolder>() {

    var models: List<LugatEntity> = arrayListOf()

    fun setData(models: List<LugatEntity>) {
        this.models = models
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dictionary, parent, false)
        return SuggestionItemViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: SuggestionItemViewHolder, position: Int) {
        holder.populateModel(models[position], listener)
    }

    fun updateItems(wordLanguage: Int, translationLanguage: Int) {
        models.forEachIndexed { index, lugatEntity ->
            lugatEntity.setLanguagePair(wordLanguage, translationLanguage)
            notifyItemChanged(index)
        }
    }
}