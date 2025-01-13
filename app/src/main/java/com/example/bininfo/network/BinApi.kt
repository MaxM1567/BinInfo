package com.example.bininfo.network

import com.example.bininfo.model.BinResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApi {
    @GET("/{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): BinResponse
}