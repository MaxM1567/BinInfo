package com.example.bininfo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bininfo.model.Request

@Dao
interface RequestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(request: Request)

    @Query("SELECT * FROM REQUESTS ORDER BY ID DESC")
    fun getAllRequests(): LiveData<List<Request>>
}