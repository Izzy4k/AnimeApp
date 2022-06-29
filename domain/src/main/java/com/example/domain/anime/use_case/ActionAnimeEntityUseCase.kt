package com.example.domain.anime.use_case

import com.example.domain.anime.entity.Anime
import com.example.domain.anime.repo.AnimeLocalRepository
import javax.inject.Inject

class ActionAnimeEntityUseCase @Inject constructor(
    private val repository: AnimeLocalRepository
) {
    suspend fun actionAnimeEntity(anime: Anime, isExists: Boolean) =
        repository.actionAnimeEntity(anime, isExists)
}