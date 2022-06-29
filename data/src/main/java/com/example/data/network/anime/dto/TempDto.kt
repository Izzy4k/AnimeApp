package com.example.data.network.anime.dto

import com.google.gson.annotations.SerializedName

data class TempDto(
    @SerializedName("data")
    val body: Body
)
