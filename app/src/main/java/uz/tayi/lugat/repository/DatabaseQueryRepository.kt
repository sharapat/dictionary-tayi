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


}