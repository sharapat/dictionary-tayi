package uz.tayi.lugat.repository

import io.reactivex.Single
import uz.tayi.lugat.constant.Constants
import uz.tayi.lugat.data.LugatDao
import uz.tayi.lugat.data.LugatEntity
import uz.tayi.lugat.helper.GsonHelper
import uz.tayi.lugat.helper.SharedPrefsHelper
import java.lang.Exception

class DatabaseQueryRepository(
    private val dao: LugatDao,
    private val sharedPrefsHelper: SharedPrefsHelper,
    private val gsonHelper: GsonHelper
) {

    fun initializeDatabaseIfAppIsFirstLaunched() : Single<String> =
        Single.create { emitter ->
            if (sharedPrefsHelper.isFirstLaunch()) {
                try {
                    val models: List<LugatEntity> = gsonHelper.getDictionaryFromLocalAssets()
                    dao.insert(models)
                    emitter.onSuccess(Constants.success)
                } catch (ex: Exception) {
                    emitter.onError(ex)
                }
            } else {
                emitter.onSuccess(Constants.success)
            }
        }
}