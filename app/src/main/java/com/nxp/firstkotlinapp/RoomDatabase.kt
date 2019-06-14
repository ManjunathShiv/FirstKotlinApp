package com.nxp.firstkotlinapp

import androidx.room.*


@Entity(tableName = "userCredentials")
data class RoomDatabase(
        @PrimaryKey(autoGenerate = true) var id: Int,
        @ColumnInfo(name = "username") var title: String,
        @ColumnInfo(name = "password") var content: String
)

@Dao
interface ToDoDao  {
    @Query("SELECT * FROM userCredentials")
    fun getAll(): List<RoomDatabase>

    @Query("SELECT * FROM userCredentials WHERE title LIKE :title")
    fun findByTitle(title: String): RoomDatabase

    @Insert
    fun insertAll(vararg todo: RoomDatabase)

    @Delete
    fun delete(todo: RoomDatabase)

    @Update
    fun updateTodo(vararg todo: RoomDatabase)
}

@Database(entities = arrayOf(RoomDatabase::class), version = 1)
abstract class AppDatabase : androidx.room.RoomDatabase() {
    abstract fun todoDao(): ToDoDao
}