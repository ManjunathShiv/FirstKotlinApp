package com.nxp.firstkotlinapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

public class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var myDao : UserDAO? = null
    private var myDatabase : UserDatabase? = null

    fun UserViewModel(application: Application) {
        super.getApplication<Application>()
        myDatabase = UserDatabase.getAppDataBase(application.applicationContext) as UserDatabase
        myDao = myDatabase?.dao
    }

    public fun insert(userEntity: UserEntity) {
        doAsync {
            myDao?.insert(userEntity)
            uiThread {
                println("Success \n")
            }
        }

    }

    public fun fetchUsers() : LiveData<List<UserEntity>>? {
        var allUsers : LiveData<List<UserEntity>>? = null
        doAsync {
            allUsers= myDao?.getAll()
        }
        return allUsers
    }

}