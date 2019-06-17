package com.nxp.firstkotlinapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

public class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var myDao : UserDAO? = null
    private var myDatabase : UserDatabase? = null

    public fun UserViewModel(application: Application) {
        super.getApplication<Application>()
        myDatabase = UserDatabase.getAppDataBase(application.applicationContext) as UserDatabase
        myDao = myDatabase?.dao
    }

    public fun insert(userEntity: UserEntity) {
        myDao?.insert(userEntity)
    }

    public fun fetchUsers() : LiveData<List<UserEntity>>? {
        var allUsers : LiveData<List<UserEntity>>? = myDao?.getAll()
        return allUsers
    }

}