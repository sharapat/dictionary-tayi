package uz.tayi.lugat.helper

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import uz.tayi.lugat.data.LugatEntity
import java.io.*
import java.lang.reflect.Type
import java.nio.charset.Charset
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream


class GsonHelper(private val context: Context, private val fileName: String) {

    private fun loadJsonFromAsset() : String {
        val json : String
        json = try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
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