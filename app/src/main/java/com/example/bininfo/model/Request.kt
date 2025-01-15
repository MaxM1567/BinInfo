package com.example.bininfo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "requests")
@Parcelize
data class Request(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bin: String,
    val scheme: String?,
    val type: String?,
    val bankName: String?,
    val countryName: String?,
    val countryCurrency: String?
) : Parcelable
