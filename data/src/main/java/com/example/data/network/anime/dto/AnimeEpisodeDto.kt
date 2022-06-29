package com.example.data.network.anime.dto

import com.google.gson.annotations.SerializedName

data class AnimeEpisodeDto(
    @SerializedName("data")
    val body: List<BodyEpisode>,
    val pagination: Pagination
)
data class BodyEpisode(
    val aired: String,
    val filler: Boolean,
    val forum_url: String,
    val mal_id: Int,
    val recap: Boolean,
    val title: String,
    val title_japanese: String,
    val title_romanji: String,
    val url: Any
)