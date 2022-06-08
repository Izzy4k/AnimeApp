package com.example.data.network.anime.dto

import com.google.gson.annotations.SerializedName

data class AnimeTopDto(
    @SerializedName("data")
    val body: List<Body>,
    val pagination: Pagination
)