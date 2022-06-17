package com.example.domain.manga.entity


data class MangaPaging(
    val manga: List<Manga>,
    val current_page: Int,
    val has_next_page: Boolean
) {
    companion object {
        fun create(manga: List<Manga>, current_page: Int, has_next_page: Boolean): MangaPaging =
            MangaPaging(manga, current_page, has_next_page)
    }
}
