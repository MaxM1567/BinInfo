package com.example.bininfo.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bininfo.repository.RequestRepository

class RequestViewModelFactory(val app: Application, private val requestRepository: RequestRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RequestViewModel(app, requestRepository) as T
    }
}
