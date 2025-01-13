package com.example.bininfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bininfo.repository.BinRepository
import kotlinx.coroutines.launch

class BinViewModel : ViewModel() {
    private val repository = BinRepository()

    private val _binData = MutableLiveData<String>()
    val binData: LiveData<String> get() = _binData

    fun fetchBinData(bin: String) {
        viewModelScope.launch {
            try {
                val result = repository.getBinData(bin)
                _binData.value = result

            } catch (e: Exception) {
                _binData.value = "Error: ${e.message}"
            }
        }
    }
}