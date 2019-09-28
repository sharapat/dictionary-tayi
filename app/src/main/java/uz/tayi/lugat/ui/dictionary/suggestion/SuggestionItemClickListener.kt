package uz.tayi.lugat.ui.dictionary.suggestion

import uz.tayi.lugat.data.local.LugatEntity

interface SuggestionItemClickListener {
    fun onItemClicked(model: LugatEntity)
}