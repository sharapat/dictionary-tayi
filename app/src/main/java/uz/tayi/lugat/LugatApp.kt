package uz.tayi.lugat

import android.app.Application
import org.koin.android.ext.android.startKoin
import uz.tayi.lugat.di.dataModule
import uz.tayi.lugat.di.helperModule
import uz.tayi.lugat.di.repositoryModule
import uz.tayi.lugat.di.viewModelModule

class LugatApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(dataModule, helperModule, repositoryModule, viewModelModule))
    }
}