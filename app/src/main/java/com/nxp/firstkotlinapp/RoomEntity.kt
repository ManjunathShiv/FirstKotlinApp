package com.nxp.firstkotlinapp

import androidx.lifecycle.LiveData
import androidx.room.*


@Entity(tableName = "userCredentials")
data class RoomEntity(
        @PrimaryKey(autoGenerate = true) var id: Int,
        @ColumnInfo(name = "username") var username: String,
        @ColumnInfo(name = "password") var password: String
)

@Dao
interface MyDao  {
    @Query("SELECT * FROM userCredentials")
    fun getAll(): List<RoomEntity>

    @Query("SELECT * from userCredentials ORDER BY word ASC")
    fun getAllUsers(): LiveData<List<RoomEntity>>

    @Query("SELECT * FROM userCredentials WHERE username LIKE :username")
    fun findByTitle(title: String): RoomEntity

    @Insert
    fun insertAll(vararg todo: RoomEntity)

    @Delete
    fun delete(todo: RoomEntity)

    @Update
    fun updateTodo(vararg todo: RoomEntity)

    @Insert
    fun insert(user: RoomEntity)

    @Query("DELETE FROM userCredentials")
    fun deleteAll()
}

