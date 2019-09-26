package uz.tayi.lugat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LugatEntity::class], version = 2, exportSchema = false)
abstract class LugatDatabase : RoomDatabase() {
    abstract fun lugatDao() : LugatDao
}