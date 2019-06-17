package com.nxp.firstkotlinapp

import androidx.lifecycle.LiveData
import androidx.room.*
import org.jetbrains.annotations.NotNull

@Entity(tableName = "userCredentials")
public class UserEntity(
        @PrimaryKey(autoGenerate = true) @NotNull var id: Int,
        @ColumnInfo(name = "username") @NotNull var username: String,
        @ColumnInfo(name = "password") @NotNull var password: String

)

