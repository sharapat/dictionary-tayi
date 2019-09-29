package uz.tayi.lugat.ui.favorite

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.tayi.lugat.data.Resource
import uz.tayi.lugat.data.local.LugatEntity
import uz.tayi.lugat.repository.DatabaseQueryRepository
import uz.tayi.lugat.ui.base.BaseViewModel

class FavoriteViewModel(private val databaseQueryRepository: DatabaseQueryRepository) : BaseViewModel() {

    val favoriteList: MutableLiveData<Resource<List<LugatEntity>>> = MutableLiveData()

    fun getFavorites() {
        compositeDisposable.add(
            databaseQueryRepository.getFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    favoriteList.value = Resource.loading()
                }
                .subscribe({
                    favoriteList.value = Resource.success(it)
                }, {
                    favoriteList.value = Resource.error(it.localizedMessage!!)
                })
        )
    }

    fun unFavorite(model: LugatEntity) {
        model.isFavorite = !model.isFavorite!!
        databaseQueryRepository.updateData(model)
        getFavorites()
    }
}