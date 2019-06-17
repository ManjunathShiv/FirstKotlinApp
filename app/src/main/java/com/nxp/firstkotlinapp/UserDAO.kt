package com.nxp.firstkotlinapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {
    @Query("SELECT * FROM userCredentials")
    fun getAll(): LiveData<List<UserEntity>>

    @Query("SELECT * from userCredentials ORDER BY word ASC")
    fun getAllUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM userCredentials WHERE username LIKE :username")
    fun findByTitle(title: String): UserEntity

    @Insert
    fun insertAll(vararg todo: UserEntity)

    @Delete
    fun delete(todo: UserEntity)

    @Update
    fun updateTodo(vararg todo: UserEntity)

    @Insert
    fun insert(user: UserEntity)

    @Query("DELETE FROM userCredentials")
    fun deleteAll()
}