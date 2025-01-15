package com.example.bininfo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bininfo.repository.BinRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch

class BinViewModel : ViewModel() {
    private val repository = BinRepository()

    private val _binData = MutableLiveData<String>()
    val binData: LiveData<String> get() = _binData

    fun fetchBinData(bin: String) {
        viewModelScope.launch {
            val result = repository.getBinData(bin)
            if (!result.toString().startsWith("Error")) {
                val jsonResult = Gson().toJson(result)
                _binData.value = jsonResult

            } else {
                _binData.value = result.toString()
            }
        }
    }
}