package com.example.bininfo.repository

import android.util.Log
import com.example.bininfo.network.RetrofitInstance

class BinRepository {
    private val api = RetrofitInstance.api

    suspend fun getBinData(bin: String): String {
        val response = api.getBinInfo(bin)
        Log.d("ABOBA", response.toString())
        return "User:\n" +
                "Country: ${response.country?.name}\n" +
                "Scheme: ${response.scheme}\n" +
                "\n" +
                "Bank:\n" +
                "Name:${response.bank?.name}\n" +
                "Url: ${response.bank?.url}\n" +
                "Phone: ${response.bank?.phone}\n" +
                "City: ${response.bank?.city}"
    }
}