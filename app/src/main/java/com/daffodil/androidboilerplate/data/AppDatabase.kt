package com.daffodil.androidboilerplate.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.daffodil.androidboilerplate.data.AppDatabase.Companion.DB_VERSION
import com.daffodil.androidboilerplate.data.dao.BoilerPlateDao
import com.daffodil.androidboilerplate.data.entity.BoilerPlateEntity

@Database(entities = [BoilerPlateEntity::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "boilerplate.db"
        const val DB_VERSION = 1
        private var DB_INSTANCE:AppDatabase?=null
        fun getInstance(context: Context): AppDatabase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
            }
            return DB_INSTANCE!!
        }

        fun destroyInstance() {
            DB_INSTANCE = null
        }
    }
    abstract fun BoilerPlateDao(): BoilerPlateDao
}
