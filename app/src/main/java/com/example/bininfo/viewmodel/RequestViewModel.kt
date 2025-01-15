package com.example.bininfo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bininfo.model.Request
import com.example.bininfo.repository.RequestRepository
import kotlinx.coroutines.launch

class RequestViewModel(app: Application, private val requestRepository: RequestRepository) :
    AndroidViewModel(app) {

    fun addRequest(request: Request) =
        viewModelScope.launch {
            requestRepository.insertRequest(request)
        }

    fun getAllRequests() = requestRepository.getAllTasks()
}