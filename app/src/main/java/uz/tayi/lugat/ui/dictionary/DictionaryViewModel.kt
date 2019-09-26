package uz.tayi.lugat.ui.dictionary

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.tayi.lugat.data.Resource
import uz.tayi.lugat.data.local.LugatEntity
import uz.tayi.lugat.repository.DatabaseQueryRepository
import uz.tayi.lugat.ui.base.BaseViewModel

class DictionaryViewModel(private val databaseQueryRepository: DatabaseQueryRepository) : BaseViewModel() {

    val dictionaryList: MutableLiveData<Resource<List<LugatEntity>>> = MutableLiveData()
    private var models: List<LugatEntity> = arrayListOf()

    fun getDictionaries(wordId: Int, translationId: Int) {
        compositeDisposable.add(
            databaseQueryRepository.getAllWords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    models = result
                    dictionaryList.value = Resource.success(setWordAndTranslation(wordId, translationId))
                },{
                    dictionaryList.value = Resource.error(it.localizedMessage!!)
                })
        )
    }

    fun setWordAndTranslation(wordId: Int, translationId: Int) : List<LugatEntity> {
        when(wordId) {
            1 -> models.forEach { it.word = it.wordEng }
            2 -> models.forEach { it.word = it.wordRus }
            3 -> models.forEach { it.word = it.wordUzbCyr }
            4 -> models.forEach { it.word = it.wordUzbLat }
        }
        when(translationId) {
            1 -> models.forEach { it.translation = it.wordEng }
            2 -> models.forEach { it.translation = it.wordRus }
            3 -> models.forEach { it.translation = it.wordUzbCyr }
            4 -> models.forEach { it.translation = it.wordUzbLat }
        }
        return models
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}