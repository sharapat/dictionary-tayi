package uz.tayi.lugat.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import uz.tayi.lugat.data.Resource
import uz.tayi.lugat.repository.DatabaseQueryRepository

class SplashViewModel(private val databaseQueryRepository: DatabaseQueryRepository) : ViewModel() {

    val process: MutableLiveData<Resource<String>> = MutableLiveData()
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    fun initializeDatabase() {
        compositeDisposable.add(
            databaseQueryRepository
                .initializeDatabaseIfAppIsFirstLaunched()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    process.value = Resource.loading()
                }
                .subscribe({
                    process.value = Resource.success(it)
                }, {
                    process.value = Resource.error(it.localizedMessage!!)
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}