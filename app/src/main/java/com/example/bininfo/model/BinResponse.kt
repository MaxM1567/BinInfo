package com.example.bininfo.model

data class BinResponse(
    val scheme: String?,
    val type: String?,
    val bank: Bank?,
    val country: Country?
) {
    data class Country(val name: String?, val currency: String?)
    data class Bank(val name: String?)
}