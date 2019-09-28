package uz.tayi.lugat.ui.dictionary

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.tayi.lugat.data.Resource
import uz.tayi.lugat.data.local.LugatEntity
import uz.tayi.lugat.repository.DatabaseQueryRepository
import uz.tayi.lugat.ui.base.BaseViewModel

class DictionaryViewModel(private val databaseQueryRepository: DatabaseQueryRepository) : BaseViewModel() {

    val suggestionList: MutableLiveData<Resource<List<LugatEntity>>> = MutableLiveData()
    private var models: List<LugatEntity> = arrayListOf()

    fun getDictionaries(wordId: Int, translationId: Int) {
        compositeDisposable.add(
            databaseQueryRepository.getAllWords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    result.forEach {
                        it.setLanguagePair(wordId, translationId)
                    }
                    suggestionList.value = Resource.success(result)
                },{
                    suggestionList.value = Resource.error(it.localizedMessage!!)
                })
        )
    }

    fun searchWordByQuery(subWord: String, wordId: Int, translationId: Int) {
        compositeDisposable.add(
            databaseQueryRepository.searchWord(subWord, wordId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    suggestionList.value = Resource.loading()
                }
                .subscribe({
                    it.forEach { lugatEntity ->
                        lugatEntity.setLanguagePair(wordId, translationId)
                    }
                    suggestionList.value = Resource.success(it)
                },{
                    suggestionList.value = Resource.error(it.localizedMessage!!)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}