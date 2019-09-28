package uz.tayi.lugat.ui.dictionary.suggestion

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.tayi.lugat.R
import uz.tayi.lugat.data.local.LugatEntity

class SuggestionItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvWord: TextView = itemView.findViewById(R.id.tvWord)

    fun populateModel(model: LugatEntity, listener: SuggestionItemClickListener) {
        tvWord.text = model.word
        itemView.setOnClickListener {
            listener.onItemClicked(model)
        }
    }
}