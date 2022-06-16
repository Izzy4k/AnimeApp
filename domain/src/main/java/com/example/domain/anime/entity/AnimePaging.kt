package com.example.domain.anime.entity

data class AnimePaging(
    val anime: List<Anime>,
    val current_page: Int,
    val has_next_page: Boolean
) {
    companion object {
        fun create(anime: List<Anime>, current_page: Int, has_next_page: Boolean) =
            AnimePaging(anime, current_page, has_next_page)
    }
}
