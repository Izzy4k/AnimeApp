package com.example.domain.anime.entity

data class AnimeEpisodePaging(
    val anime: List<AnimeEpisode>,
    val current_page: Int,
    val has_next_page: Boolean
) {
    companion object {
        fun create(
            anime: List<AnimeEpisode>,
            current_page: Int,
            has_next_page: Boolean
        ): AnimeEpisodePaging =
            AnimeEpisodePaging(anime, current_page, has_next_page)
    }
}
