package com.example.domain.manga.entity

data class Manga(
    val id: Int,
    val image: String,
    val title: String,
    val author: String
) {
    companion object {
        fun create(id: Int, image: String, title: String, author: String): Manga = Manga(
            id, image, title, author
        )
    }
}
