package com.example.domain.anime.entity

data class AnimeEpisode(
    val id: Int,
    val title: String,
    val aired: String
) {
    companion object {
        fun create(id: Int, title: String, aired: String): AnimeEpisode = AnimeEpisode(
            id, title, aired
        )
    }
}
