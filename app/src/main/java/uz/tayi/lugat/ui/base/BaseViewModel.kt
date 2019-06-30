package uz.tayi.lugat.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    protected val compositeDisposable : CompositeDisposable = CompositeDisposable()
}