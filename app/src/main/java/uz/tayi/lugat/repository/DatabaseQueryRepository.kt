package uz.tayi.lugat.repository

import io.reactivex.Single
import uz.tayi.lugat.constant.Constants
import uz.tayi.lugat.data.local.LugatDao
import uz.tayi.lugat.data.local.LugatEntity
import uz.tayi.lugat.helper.GsonHelper
import java.lang.Exception

class DatabaseQueryRepository (
    private val dao: LugatDao,
    private val gsonHelper: GsonHelper
) {

    fun initializeDatabase() : Single<String> {
        return Single.create<String> { emitter ->
            try {
                dao.insert(gsonHelper.getDictionaryFromLocalAssets())
            } catch (ex: Exception) {
                emitter.onError(ex)
            }
            emitter.onSuccess(Constants.success)
        }
    }

    fun getAllWords(): Single<List<LugatEntity>> =
        dao.getAllWords()

    fun searchWord(subWord: String, wordId: Int): Single<List<LugatEntity>> {
        return when(wordId) {
            0 -> dao.searchByEng("%$subWord%")
            1 -> dao.searchByRus("%$subWord%")
            2 -> dao.searchByUzbCyr("%$subWord%")
            3 -> dao.searchByUzbLat("%$subWord%")
            else -> dao.searchByEng("%$subWord%")
        }
    }

    fun updateData(model: LugatEntity) {
        dao.update(model)
    }

    fun updateData(models: List<LugatEntity>) : Single<String> {
        return Single.create<String> { emitter ->
            dao.update(models)
            emitter.onSuccess(Constants.success)
        }
    }

    fun getFavorites() : Single<List<LugatEntity>> {
        return dao.getFavorites()
    }

    fun getModelById(id: Int) : LugatEntity {
        return dao.getModelById(id)
    }
}