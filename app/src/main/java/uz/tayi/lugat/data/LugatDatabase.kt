package uz.tayi.lugat.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LugatEntity::class], version = 1)
abstract class LugatDatabase : RoomDatabase() {
    abstract fun lugatDao() : LugatDao
}