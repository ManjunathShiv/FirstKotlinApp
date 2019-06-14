package com.nxp.firstkotlinapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(RoomEntity::class), version = 1)
abstract class AppDatabase : androidx.room.RoomDatabase() {
    abstract fun myDao(): MyDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "AppDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

private class AppDatabaseCallback(
        private val scope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        var INSTANCE: AppDatabase? = null
        INSTANCE?.let { database : AppDatabase ->
            scope.launch(Dispatchers.IO) {
                populateDatabase(database.myDao())
            }
        }
    }

    fun populateDatabase(myDao: MyDao) {
        myDao.deleteAll()

        var user = RoomEntity(1, "Manjunath", "Shivakumara")
        myDao.insert(user)

        user = RoomEntity(2, "Suneetha", "Shivakumar")
        myDao.insert(user)
    }
}
