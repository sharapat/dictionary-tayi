package uz.tayi.lugat

import android.app.Application
import org.koin.android.ext.android.startKoin
import uz.tayi.lugat.di.*

class LugatApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(dataModule, helperModule, repositoryModule, viewModelModule))
    }
}