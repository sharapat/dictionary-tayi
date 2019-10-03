package uz.tayi.lugat.ui.history.list

import uz.tayi.lugat.data.local.LugatEntity

interface OnHistoryItemClickListener {
    fun onItemClicked(model: LugatEntity)
}