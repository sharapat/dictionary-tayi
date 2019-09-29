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

    @ColumnInfo(name = "favorite")
    var isFavorite: Boolean? = false,

    var word: String? = "",

    var translation: String? = ""
) {
    companion object {
        const val LANGUAGE_ENG = 0
        const val LANGUAGE_RUS = 1
        const val LANGUAGE_UZB_CYR = 2
        const val LANGUAGE_UZB_LAT = 3
    }

    fun setLanguagePair(wordId: Int, translationId: Int) {
        when (wordId) {
            LANGUAGE_ENG -> this.word = wordEng
            LANGUAGE_RUS -> this.word = wordRus
            LANGUAGE_UZB_CYR -> this.word = wordUzbCyr
            LANGUAGE_UZB_LAT -> this.word = wordUzbLat
            else -> this.word = wordEng
        }
        when (translationId) {
            LANGUAGE_ENG -> this.translation = wordEng
            LANGUAGE_RUS -> this.translation = wordRus
            LANGUAGE_UZB_CYR -> this.translation = wordUzbCyr
            LANGUAGE_UZB_LAT -> this.translation = wordUzbLat
            else -> this.translation = wordRus
        }
    }

    fun getMessageForShare() : String {
        return "$wordEng\n\n$wordRus\n\n$wordUzbCyr\n\n$wordUzbLat}"
    }
}