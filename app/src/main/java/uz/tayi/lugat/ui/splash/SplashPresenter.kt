package uz.tayi.lugat.ui.splash

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.tayi.lugat.constant.Constants
import uz.tayi.lugat.data.Resource
import uz.tayi.lugat.helper.SharedPrefsHelper
import uz.tayi.lugat.repository.DatabaseQueryRepository
import uz.tayi.lugat.ui.base.BaseViewModel

class SplashPresenter(
    private val sharedPrefsHelper: SharedPrefsHelper,
    private val databaseQueryRepository: DatabaseQueryRepository) : BaseViewModel() {

    val process: MutableLiveData<Resource<String>> = MutableLiveData()

    lateinit var view: SplashView

    fun init(view: SplashView) {
        this.view = view
    }

    fun getDataIfAppIsFirstLaunched() {
        if (sharedPrefsHelper.isFirstLaunch()) {
            compositeDisposable.add(
                databaseQueryRepository.initializeDatabase()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        process.value = Resource.success(Constants.success)
                        sharedPrefsHelper.setFirstLaunch()
                    },{
                        process.value = Resource.error(it.localizedMessage!!)
                    })
            )
        } else {
            view.goToMainScreen()
        }

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}