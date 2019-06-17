package com.nxp.firstkotlinapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
public abstract class UserDatabase : RoomDatabase() {

    public  abstract var dao : UserDAO

    companion object {
        var userRoomInstance: UserDatabase? = null

        fun getAppDataBase(context: Context): UserDatabase? {

            if (userRoomInstance == null){
                synchronized(UserDatabase::class){
                    userRoomInstance = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "myDB").build()
                }
            }
            return userRoomInstance
        }

        fun destroyDataBase(){
            userRoomInstance = null
        }
    }
}