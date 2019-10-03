package uz.tayi.lugat.data.local

import androidx.room.*
import io.reactivex.Single

@Dao
interface LugatDao {

    @Query("SELECT * FROM dictionary WHERE id=:id")
    fun getModelById(id: Int) : LugatEntity

    @Query("SELECT * FROM dictionary")
    fun getAllWords() : Single<List<LugatEntity>>

    @Query("SELECT * FROM dictionary WHERE word_eng LIKE :subWord")
    fun searchByEng(subWord: String) : Single<List<LugatEntity>>

    @Query("SELECT * FROM dictionary WHERE word_rus LIKE :subWord")
    fun searchByRus(subWord: String) : Single<List<LugatEntity>>

    @Query("SELECT * FROM dictionary WHERE word_uzb_cyr LIKE :subWord")
    fun searchByUzbCyr(subWord: String) : Single<List<LugatEntity>>

    @Query("SELECT * FROm dictionary WHERE word_uzb_lat LIKE :subWord")
    fun searchByUzbLat(subWord: String) : Single<List<LugatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<LugatEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(models: List<LugatEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(model: LugatEntity)

    @Query("SELECT * FROM dictionary WHERE favorite = 1")
    fun getFavorites(): Single<List<LugatEntity>>

    @Query("SELECT * FROM dictionary WHERE last_accessed > 0 ORDER BY last_accessed DESC LIMIT 20")
    fun getHistories(): Single<List<LugatEntity>>
}