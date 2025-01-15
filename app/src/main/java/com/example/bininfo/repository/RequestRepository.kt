package com.example.bininfo.repository

import com.example.bininfo.database.RequestDatabase
import com.example.bininfo.model.Request

class RequestRepository(private val db: RequestDatabase) {
    suspend fun insertRequest(request: Request) = db.getRequestDao().insertRequest(request)

    fun getAllRequests() = db.getRequestDao().getAllRequests()
}