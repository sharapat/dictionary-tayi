package uz.tayi.lugat.ui.favorite.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.tayi.lugat.R
import uz.tayi.lugat.data.local.LugatEntity

class FavoritesAdapter(private val listener: OnFavoriteItemClickListener) : RecyclerView.Adapter<FavoriteViewHolder>() {

    private var models: List<LugatEntity> = arrayListOf()

    fun setData(models: List<LugatEntity>) {
        this.models = models
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorites, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int =
        models.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.populateModel(models[position], listener)
    }
}