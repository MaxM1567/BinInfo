package com.example.bininfo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bininfo.model.Request

@Database(entities = [Request::class], version = 1)
abstract class RequestDatabase : RoomDatabase() {

    abstract fun getRequestDao(): RequestDao

    companion object {
        @Volatile
        private var instance: RequestDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RequestDatabase::class.java,
                "request_db"
            ).build()
    }
}