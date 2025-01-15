package com.example.bininfo.repository

import android.util.Log
import com.example.bininfo.network.BinApi

class BinRepository(private val api: BinApi) {

    suspend fun getBinData(bin: String): Any {
        try {
            val response = api.getBinInfo(bin)
            return mapOf(
                "bin" to bin,
                "scheme" to response.scheme,
                "type" to response.type,
                "bank" to mapOf(
                    "name" to response.bank?.name,
                ),
                "country" to mapOf(
                    "name" to response.country?.name,
                    "currency" to response.country?.currency
                )
            )
        } catch (e: Exception) {
            Log.e("ERR", "Error: $e")
            return "Error: $e"
        }
    }
}