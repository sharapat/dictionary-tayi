package uz.tayi.lugat.ui.dictionary.list

import uz.tayi.lugat.data.local.LugatEntity

interface DictionaryItemClickListener {
    fun onItemClicked(model: LugatEntity)
}