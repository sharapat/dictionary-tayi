package uz.tayi.lugat.repository

import uz.tayi.lugat.data.LugatEntity
import uz.tayi.lugat.helper.GsonHelper

class AssetRepository(private val gsonHelper: GsonHelper) {

    fun readDatabaseFromAssets() : List<LugatEntity> {
        return gsonHelper.getDictionaryFromLocalAssets()
    }
}