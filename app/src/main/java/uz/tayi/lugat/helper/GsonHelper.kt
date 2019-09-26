package uz.tayi.lugat.helper

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import uz.tayi.lugat.data.local.LugatEntity
import java.io.*
import java.lang.reflect.Type
import java.nio.charset.Charset

class GsonHelper(private val context: Context) {

    private fun loadJsonFromAsset() : String {
        var json = ""
        json = try {
            val fileNames = context.assets.list("jsons")
            fileNames?.forEachIndexed { index, s ->
                val inputStream: InputStream = context.assets.open("jsons/db_${index+1}.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json += String(buffer, Charset.forName("UTF-8"))
            }
            Log.d("resultat", json)
            json
        } catch (ex: IOException) {
            ex.printStackTrace()
            ""
        }
        return json
    }

    fun getDictionaryFromLocalAssets() : List<LugatEntity> {
        val json: String = loadJsonFromAsset()
        if (json.isEmpty()) {
            return arrayListOf()
        }
        val listType: Type = object : TypeToken<List<LugatEntity>>() { }.type
        return GsonBuilder().create().fromJson(json, listType)
    }
}