package uz.tayi.lugat.ui.favorite

import uz.tayi.lugat.data.local.LugatEntity

interface OnFavoriteItemClickListener {
    fun onFavoriteButtonClicked(model: LugatEntity)
    fun onFavoriteItemClicked(model: LugatEntity)
}