package com.example.bininfo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bininfo.model.Request

@Database(entities = [Request::class], version = 1)
abstract class RequestDatabase : RoomDatabase() {
    abstract fun getRequestDao(): RequestDao
}