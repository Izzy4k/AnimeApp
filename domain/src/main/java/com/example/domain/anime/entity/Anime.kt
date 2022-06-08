package com.example.domain.anime.entity

data class Anime(
    val id: Int,
    val image: String,
    val title: String,
    val genre: String
) {
    companion object {
        fun create(
            id: Int,
            image: String,
            title: String,
            genre: String
        ) = Anime(
            id, image, title, genre
        )
    }
}
