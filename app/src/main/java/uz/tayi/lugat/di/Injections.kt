package uz.tayi.lugat.di

import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import uz.tayi.lugat.data.local.LugatDao
import uz.tayi.lugat.data.local.LugatDatabase
import uz.tayi.lugat.helper.GsonHelper
import uz.tayi.lugat.helper.SharedPrefsHelper
import uz.tayi.lugat.repository.DatabaseQueryRepository
import uz.tayi.lugat.ui.dictionary.DictionaryViewModel
import uz.tayi.lugat.ui.splash.SplashPresenter

private const val databaseName = "dictionary_database"

val dataModule = module {
        single {
                Room.databaseBuilder(get(), LugatDatabase::class.java, databaseName).build()
        }
        single { provideGson() }
        single { provideDao(get()) }
}

val helperModule = module {
        single { GsonHelper(androidContext()) }
        single { SharedPrefsHelper(androidContext()) }
}

val repositoryModule = module {
        single { DatabaseQueryRepository(get(), get()) }
}

val viewModelModule = module {
        viewModel { SplashPresenter(get(), get()) }
        viewModel { DictionaryViewModel(get()) }
}

fun provideGson() : Gson =
        GsonBuilder().setLenient().create()

fun provideDao(lugatDatabase: LugatDatabase) : LugatDao =
        lugatDatabase.lugatDao()
