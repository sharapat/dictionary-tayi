package uz.tayi.lugat.data

import androidx.room.*
import io.reactivex.Single

@Dao
interface LugatDao {

    @Query("SELECT * FROM dictionary")
    fun getAllWords() : Single<List<LugatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<LugatEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(model: LugatEntity)
}