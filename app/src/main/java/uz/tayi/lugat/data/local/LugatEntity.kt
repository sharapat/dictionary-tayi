package uz.tayi.lugat.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "dictionary")
data class LugatEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Int,

    @ColumnInfo(name="word_rus")
    @SerializedName("word_rus")
    var wordRus : String = "",

    @ColumnInfo(name="word_eng")
    @SerializedName("word_eng")
    var wordEng: String = "",

    @ColumnInfo(name="word_uzb_cyr")
    @SerializedName("word_uzb_cyr")
    var wordUzbCyr: String = "",

    @ColumnInfo(name="word_uzb_lat")
    @SerializedName("word_uzb_lat")
    var wordUzbLat: String = "",

    @ColumnInfo(name = "last_accessed")
    var lastAccessed: Long? = 0,

    @ColumnInfo(name = "fovorite")
    var isFavorite: Boolean? = false,

    var word: String? = "",

    var translation: String? = ""
)