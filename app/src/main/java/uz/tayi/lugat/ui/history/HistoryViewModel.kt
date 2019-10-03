package uz.tayi.lugat.ui.history

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.tayi.lugat.data.Resource
import uz.tayi.lugat.data.local.LugatEntity
import uz.tayi.lugat.repository.DatabaseQueryRepository
import uz.tayi.lugat.ui.base.BaseViewModel

class HistoryViewModel(private val databaseQueryRepository: DatabaseQueryRepository) : BaseViewModel() {

    val historyList: MutableLiveData<Resource<List<LugatEntity>>> = MutableLiveData()

    fun getHistories() {
        compositeDisposable.add(
            databaseQueryRepository.getHistories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    historyList.value = Resource.loading()
                }
                .subscribe({
                    Log.d("wordandtranslation", "${it[0].word} ${it[0].translation}")
                    historyList.value = Resource.success(it)
                }, {
                    historyList.value = Resource.error(it.localizedMessage!!)
                })
        )
    }
}