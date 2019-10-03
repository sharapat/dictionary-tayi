package uz.tayi.lugat.ui.history.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.tayi.lugat.R
import uz.tayi.lugat.data.local.LugatEntity
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class HistoryItemViewHolder(itemView: View, private val listener: OnHistoryItemClickListener) : RecyclerView.ViewHolder(itemView) {

    private val word: TextView = itemView.findViewById(R.id.history_list_word)
    private val date: TextView = itemView.findViewById(R.id.history_list_date)
    fun populateModel(model: LugatEntity) {
        word.text = model.wordEng
        val dateString =
            SimpleDateFormat("dd/MM/yyyy, HH:mm", Locale.ROOT).format(Date(model.lastAccessed!!))
        date.text = dateString
        itemView.setOnClickListener {
            listener.onItemClicked(model)
        }
    }
}