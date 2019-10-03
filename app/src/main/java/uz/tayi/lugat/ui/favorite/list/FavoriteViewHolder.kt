package uz.tayi.lugat.ui.favorite.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.tayi.lugat.R
import uz.tayi.lugat.data.local.LugatEntity

class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val favoriteWord: TextView = itemView.findViewById(R.id.favoriteWord)
    private val favoriteButton: ImageView = itemView.findViewById(R.id.favourite_button)

    fun populateModel(model: LugatEntity, listener: OnFavoriteItemClickListener) {
        favoriteWord.text = model.word
        favoriteButton.setOnClickListener {
            listener.onFavoriteButtonClicked(model)
        }
        itemView.setOnClickListener {
            listener.onFavoriteItemClicked(model)
        }
    }

}