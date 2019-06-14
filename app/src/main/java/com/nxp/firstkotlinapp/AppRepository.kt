package com.nxp.firstkotlinapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppRepository(private val MyDao:MyDao) {

    val allUsers: LiveData<List<RoomEntity>> = MyDao.getAllUsers()

    fun insert(roomEntity: RoomEntity) {
        MyDao.insertAll(roomEntity)
    }
}

class EntityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AppRepository
    val allUsers: LiveData<List<RoomEntity>>

    init {
        val myDao = AppDatabase.getDatabase(application).myDao()
        repository = AppRepository(myDao)
        allUsers = repository.allUsers
    }

    fun insert(user: RoomEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)
    }
}