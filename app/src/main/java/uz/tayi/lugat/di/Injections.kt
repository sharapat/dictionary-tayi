package uz.tayi.lugat.di

import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import uz.tayi.lugat.data.LugatDao
import uz.tayi.lugat.data.LugatDatabase
import uz.tayi.lugat.helper.GsonHelper
import uz.tayi.lugat.helper.SharedPrefsHelper
import uz.tayi.lugat.repository.AssetRepository
import uz.tayi.lugat.repository.DatabaseQueryRepository
import uz.tayi.lugat.ui.splash.SplashViewModel

private const val databaseName = "dictionary_database"
private const val databaseFileName = "lugat.json.zip"

val dataModule = module {
        single {
                Room.databaseBuilder(get(), LugatDatabase::class.java, databaseName).build()
        }
        single { provideGson() }
        single { provideDao(get()) }
}

val helperModule = module {
        single { GsonHelper(androidContext(), databaseFileName) }
        single { SharedPrefsHelper(androidContext()) }
}

val repositoryModule = module {
        single { AssetRepository(get()) }
        single { DatabaseQueryRepository(get(), get(), get()) }
}

val viewModelModule = module {
        viewModel { SplashViewModel(get()) }
}

fun provideGson() : Gson =
        GsonBuilder().setLenient().create()

fun provideDao(lugatDatabase: LugatDatabase) : LugatDao =
        lugatDatabase.lugatDao()